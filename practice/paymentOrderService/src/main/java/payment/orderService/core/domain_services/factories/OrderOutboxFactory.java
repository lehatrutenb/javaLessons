package payment.orderService.core.domain_services.factories;

import org.springframework.stereotype.Component;
import payment.orderService.core.domain_services.ifactories.IorderOutboxFactory;
import payment.orderService.core.entities.*;
import payment.orderService.core.repositoryEntities.OrderOutbox;

@Component
public class OrderOutboxFactory implements IorderOutboxFactory {
    @Override
    public OrderOutbox create(UserId userId, int cost, OrderDescription orderDescription, OrderStatus status) {
        return new OrderOutbox(userId, cost, orderDescription, status);
    }
}
