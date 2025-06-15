package payment.paymentService.adapters.repositories.innerRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import payment.paymentService.core.entities.OrderStatus;
import payment.paymentService.core.repositoryEntities.OrderOutbox;

import java.util.Optional;

public interface IinnerOrderOutboxRepository extends JpaRepository<OrderOutbox, Integer> {
    public Optional<OrderOutbox> findFirstByStatus(OrderStatus orderStatus);
}
