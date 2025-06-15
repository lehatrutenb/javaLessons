package payment.paymentService.useCases.iShared;

import payment.paymentService.core.entities.Order;
import payment.paymentService.core.entities.OrderId;

import java.util.Optional;

public interface IorderRepository {
    public Order save(Order order);
    public Optional<Order> findById(OrderId orderId);
}
