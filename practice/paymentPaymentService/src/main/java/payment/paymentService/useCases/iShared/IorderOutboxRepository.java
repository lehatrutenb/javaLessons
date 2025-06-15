package payment.paymentService.useCases.iShared;

import payment.paymentService.core.entities.OrderId;
import payment.paymentService.core.entities.OrderStatus;
import payment.paymentService.core.repositoryEntities.OrderOutbox;

import java.util.Optional;

public interface IorderOutboxRepository {
    public OrderOutbox save(OrderOutbox order);
    public Optional<OrderOutbox> findFirstByStatus(OrderStatus orderStatus); // better to choose random, but it cost a lot
    public void deleteById(OrderId orderId);
}
