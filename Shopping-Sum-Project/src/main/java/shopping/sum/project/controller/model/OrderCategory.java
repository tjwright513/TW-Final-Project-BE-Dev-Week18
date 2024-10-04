package shopping.sum.project.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import shopping.sum.project.entity.Category;
import shopping.sum.project.entity.Item;

@Data
@NoArgsConstructor
public class OrderCategory {

	private Long categoryId;
	private String categoryName;
	private String taxStatus;
	private Set<OrderItem> items = new HashSet<>();
	
	public OrderCategory(Category category) {
		categoryId = category.getCategoryId();
		categoryName = category.getCategoryName();
		taxStatus = category.getTaxStatus();
		
		for(Item item : category.getItems()) {
			items.add(new OrderItem(item));
		}
	}
}
