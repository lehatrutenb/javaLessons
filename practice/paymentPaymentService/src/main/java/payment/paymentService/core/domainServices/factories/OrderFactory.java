package payment.paymentService.core.domainServices.factories;

import org.springframework.stereotype.Component;
import payment.paymentService.core.domainServices.iFactories.IorderFactory;
import payment.paymentService.core.entities.Order;
import payment.paymentService.core.entities.OrderId;
import payment.paymentService.core.entities.OrderStatus;
import payment.paymentService.core.entities.UserId;
import payment.paymentService.core.repositoryEntities.OrderOutbox;

@Component
public class OrderFactory implements IorderFactory {
    @Override
    public Order create(OrderOutbox orderOutbox) {
        return new Order(orderOutbox.getId(), orderOutbox.getUserId(), orderOutbox.getCost(), orderOutbox.getStatus());
    }
}
