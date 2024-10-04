package shopping.sum.project.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shopping.sum.project.controller.model.OrderData;
import shopping.sum.project.controller.model.OrderItem;
import shopping.sum.project.dao.ItemDao;
import shopping.sum.project.dao.OrderDao;
import shopping.sum.project.entity.Item;
import shopping.sum.project.entity.Orders;

@Service
public class ShoppingSumProjectService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ItemDao itemDao;
	
	@Transactional(readOnly = false)
	public OrderData saveOrder(OrderData orderData) {
		Long orderId = orderData.getOrderId();
		Orders orders = findOrCreateOrders(orderId);
		copyOrdersFields(orders, orderData);
		return new OrderData(orderDao.save(orders));
	}

	private void copyOrdersFields(Orders orders, OrderData orderData) {
		orders.setOrderId(orderData.getOrderId());
		//order.setOrderDate(orderData.getOrderDate());
		orders.setQuanity(orderData.getQuanity());
		orders.setFinalCost(orderData.getFinalCost());
	}

	private Orders findOrCreateOrders(Long orderId) {
		if(Objects.isNull(orderId)) {
			return new Orders();
		}else {
			findOrdersById(orderId);
		}
		return null;
	}

	private Orders findOrdersById(Long orderId) {
		return orderDao.findById(orderId).orElseThrow(() -> new NoSuchElementException("Orders with ID+" + orderId + " does not exist."));
	}
	
	private void copyItemFields(Item item, OrderItem orderItem) {
		item.setItemId(orderItem.getItemId());
		item.setItemName(orderItem.getName());
		item.setCost(orderItem.getCost());
	}


	
	private Item findItemById(Long orderId, Long itemId) {
		Item item = itemDao.findById(itemId).orElseThrow(() -> new NoSuchElementException("Item with Id=" + itemId + "must be added."));
		for (Orders orders : item.getOrders()) {
			if(orders.getOrderId() != orderId) {
				throw new IllegalArgumentException("The Item with ID=" + itemId + "is not in order with ID=" + orderId + ".");
			}
		}
				return item;
	}

   
	@Transactional(readOnly = true)
	public List<OrderData> retrieveAllOrders() {
		List<Orders> orders = orderDao.findAll();
		List<OrderData> data = new LinkedList<>();
		for (Orders orders1 : orders) {
			OrderData od = null;
			od.getItems().clear();
			data.add(od);
		}
		return data;
	}

    @Transactional(readOnly = true)
	public OrderData retrieveOrderById(Long orderId) {
		return new  OrderData(findOrdersById(orderId));
	}

	public void deleteOrderById(Long orderId) {
		Orders orders = findOrdersById(orderId);
		orderDao.delete(orders);
		
	}

	public OrderItem saveItem(Long orderId, OrderItem orderItem) {
		Long itemId = orderItem.getItemId();
		Item item = findOrCreateItem(orderId, itemId);
		copyItemFields(item, orderItem);
		return new OrderItem(item);
	}

	private Item findOrCreateItem(Long orderId, Long itemId) {
		if(Objects.isNull(orderId)) {
		return new Item();
	}
		return findItemById(orderId, itemId);
	}

 







}
