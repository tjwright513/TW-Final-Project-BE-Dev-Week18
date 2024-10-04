package shopping.sum.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import shopping.sum.project.entity.Orders;

public interface OrderDao extends JpaRepository<Orders, Long> {

}
