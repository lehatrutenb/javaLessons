package payment.paymentService.core.domainServices.iFactories;

import payment.paymentService.core.entities.Order;
import payment.paymentService.core.entities.OrderId;
import payment.paymentService.core.entities.OrderStatus;
import payment.paymentService.core.entities.UserId;
import payment.paymentService.core.repositoryEntities.OrderOutbox;

public interface IorderFactory {
    public Order create(OrderOutbox orderOutbox);
}
