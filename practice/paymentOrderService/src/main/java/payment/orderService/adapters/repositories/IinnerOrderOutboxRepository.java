package payment.orderService.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import payment.orderService.core.entities.Order;
import payment.orderService.core.entities.OrderStatus;
import payment.orderService.core.repositoryEntities.OrderOutbox;

import java.util.Optional;

public interface IinnerOrderOutboxRepository extends JpaRepository<OrderOutbox, Integer> {
    Optional<OrderOutbox> findFirstByStatus(OrderStatus orderStatus);
}
