package payment.orderService.useCases.ishared;

import payment.orderService.core.entities.Order;
import payment.orderService.core.entities.OrderId;
import payment.orderService.core.entities.UserId;

import java.util.List;
import java.util.Optional;

public interface IorderRepository {
    public Order save(Order order);
    public Optional<Order> findById(OrderId orderId);
    public List<Order> findByUserId(UserId userId);
}
