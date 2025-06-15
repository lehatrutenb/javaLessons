package payment.paymentService.adapters.repositories.innerRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import payment.paymentService.core.entities.Order;

public interface IinnerOrderRepository extends JpaRepository<Order, Integer> {
}
