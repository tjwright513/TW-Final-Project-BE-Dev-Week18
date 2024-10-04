package shopping.sum.project.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data

public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;
	private String itemName;
	private double cost;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne (cascade = CascadeType.PERSIST)
	
	@JoinColumn (name = "category_id")
	private Category category;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "items" , cascade = CascadeType.PERSIST)
	private Set<Orders> orders = new HashSet<>();
	
		
}
