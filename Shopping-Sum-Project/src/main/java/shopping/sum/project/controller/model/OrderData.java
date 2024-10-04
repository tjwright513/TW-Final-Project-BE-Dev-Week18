package shopping.sum.project.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import shopping.sum.project.entity.Item;
import shopping.sum.project.entity.Orders;

@Data
@NoArgsConstructor
public class OrderData {
  private Long orderId;
  //private Calendar orderDate;
  private int quanity;
  private double finalCost;
  private Set<OrderItem> items = new HashSet<>();
  
  public OrderData(Orders orders) {
	  orderId = orders.getOrderId();
	  //orderDate = order.getOrderDate();
	  quanity = orders.getQuanity();
	  finalCost = orders.getFinalCost();
	  
	  for(Item item : orders.getItems()) {
		  items.add(new OrderItem(item));
		  
	  }
	  
  }


}
