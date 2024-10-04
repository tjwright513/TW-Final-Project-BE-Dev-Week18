package shopping.sum.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import shopping.sum.project.entity.Item;

public interface ItemDao extends JpaRepository<Item, Long> {

}
