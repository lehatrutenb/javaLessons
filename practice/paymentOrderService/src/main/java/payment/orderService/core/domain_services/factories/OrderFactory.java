package payment.orderService.core.domain_services.factories;

import org.springframework.stereotype.Component;
import payment.orderService.core.domain_services.ifactories.IorderFactory;
import payment.orderService.core.entities.*;
import payment.orderService.core.repositoryEntities.OrderOutbox;

@Component
public class OrderFactory implements IorderFactory {
    @Override
    public Order create(OrderId orderId, UserId userId, int cost, OrderDescription orderDescription, OrderStatus status) {
        return new Order(orderId, userId, cost, orderDescription, status);
    }

    @Override
    public Order create(OrderOutbox orderOutbox) {
        return new Order(orderOutbox.getId(), orderOutbox.getUserId(), orderOutbox.getCost(),
                orderOutbox.getDescription(), orderOutbox.getStatus());
    }
}
