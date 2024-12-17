package epicode.it.pizzeria.repository;

import epicode.it.pizzeria.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
