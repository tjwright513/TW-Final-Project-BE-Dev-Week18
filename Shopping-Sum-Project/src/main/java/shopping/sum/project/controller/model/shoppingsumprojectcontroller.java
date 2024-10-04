package shopping.sum.project.controller.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import shopping.sum.project.entity.Category;
import shopping.sum.project.service.ShoppingSumProjectService;

@RestController
@RequestMapping("/shoppingsum")
@Slf4j
public class shoppingsumprojectcontroller {
    @Autowired
	private ShoppingSumProjectService shoppingSumProjectService;
	
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public OrderData insertOrder(@RequestBody OrderData orderData) {
    	log.info("Creating your order ()", orderData);
		return shoppingSumProjectService.saveOrder(orderData);
    }
    
    @PutMapping("/{orderId}")
    public OrderData updateOrder(@PathVariable Long orderId, @RequestBody OrderData orderData) {
    	orderData.setOrderId(orderId);
    	log.info("Udateing Orders ()", orderData);
    	return shoppingSumProjectService.saveOrder(orderData);
    }

    @PostMapping("/{orderId}/item")
    @ResponseStatus(code = HttpStatus.CREATED)
    public OrderItem addItemToOrder(@PathVariable Long orderId, @RequestBody OrderItem orderItem) {
    	log.info("Adding item () to order with ID=()", orderItem, orderId);
		return shoppingSumProjectService.saveItem(orderId, orderItem);
	

    }
    

	@GetMapping
    public List<OrderData> retrieveAllOrders() {
    	log.info("Displaying all orders");
    	return shoppingSumProjectService.retrieveAllOrders();
    }
    
    @GetMapping("/{orderId}")
    public OrderData retrieveOrderById(@PathVariable Long orderId) {
    	log.info("Displaying order with ID=()", orderId);
    	return shoppingSumProjectService.retrieveOrderById(orderId);
    }
    
    @DeleteMapping("/{orderId}")
    public Map<String, String> deleteOrderById(@PathVariable Long orderId){
    	log.info("Deleting order with ID=()", orderId);
    	shoppingSumProjectService.deleteOrderById(orderId);
    	return Map.of("message", "Deleting order with ID=" + orderId);
    }
    
    
    
    
}