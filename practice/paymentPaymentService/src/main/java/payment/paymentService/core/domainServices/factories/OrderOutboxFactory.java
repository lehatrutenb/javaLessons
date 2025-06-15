package payment.paymentService.core.domainServices.factories;

import org.springframework.stereotype.Component;
import payment.paymentService.core.domainServices.holders.IorderOutboxFactory;
import payment.paymentService.core.domainServices.iFactories.IorderFactory;
import payment.paymentService.core.entities.Order;
import payment.paymentService.core.entities.OrderId;
import payment.paymentService.core.entities.OrderStatus;
import payment.paymentService.core.entities.UserId;
import payment.paymentService.core.repositoryEntities.OrderOutbox;

@Component
public class OrderOutboxFactory implements IorderOutboxFactory {
    @Override
    public OrderOutbox create(OrderId orderId, UserId userId, int cost, OrderStatus orderStatus) {
        return new OrderOutbox(orderId, userId, cost, orderStatus);
    }
}
