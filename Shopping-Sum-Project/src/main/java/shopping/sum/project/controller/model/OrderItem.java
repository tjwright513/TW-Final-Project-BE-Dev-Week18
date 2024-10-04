package shopping.sum.project.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import shopping.sum.project.entity.Item;

@Data
@NoArgsConstructor
public class OrderItem {

	private long itemId;
	private String name;
	private double cost;
	
	public OrderItem(Item item) {
		itemId = item.getItemId();
		name = item.getItemName();
		cost = item.getCost();
	}
}
