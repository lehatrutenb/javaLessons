package payment.orderService.core.domain_services.ifactories;

import payment.orderService.core.entities.*;
import payment.orderService.core.repositoryEntities.OrderOutbox;

public interface IorderOutboxFactory {
    public OrderOutbox create(UserId userId, int cost, OrderDescription orderDescription, OrderStatus status);
}
