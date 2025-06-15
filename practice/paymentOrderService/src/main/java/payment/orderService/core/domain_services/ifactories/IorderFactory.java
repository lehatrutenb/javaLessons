package payment.orderService.core.domain_services.ifactories;

import payment.orderService.core.entities.*;
import payment.orderService.core.repositoryEntities.OrderOutbox;

public interface IorderFactory {
    public Order create(OrderId orderId, UserId userId, int cost, OrderDescription orderDescription, OrderStatus status);
    public Order create(OrderOutbox orderOutbox);
}
