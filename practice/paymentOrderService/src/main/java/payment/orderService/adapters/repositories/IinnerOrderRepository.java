package payment.orderService.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import payment.orderService.core.entities.Order;

import java.util.List;

public interface IinnerOrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(Integer userId);
}
