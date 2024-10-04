package shopping.sum.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import shopping.sum.project.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Long>{

}
