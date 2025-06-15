package payment.orderService.useCases.ishared;

import payment.orderService.core.entities.Order;
import payment.orderService.core.entities.OrderId;
import payment.orderService.core.entities.OrderStatus;
import payment.orderService.core.repositoryEntities.OrderOutbox;

import java.util.Optional;

public interface IorderOutboxRepository {
    public OrderOutbox save(OrderOutbox order);
    public Optional<OrderOutbox> findFirstByStatus(OrderStatus orderStatus); // better to choose random, but it cost a lot
    public void deleteById(OrderId orderId);
}
