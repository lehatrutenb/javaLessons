package payment.paymentService.core.domainServices.holders;

import payment.paymentService.core.entities.OrderId;
import payment.paymentService.core.entities.OrderStatus;
import payment.paymentService.core.entities.UserId;
import payment.paymentService.core.repositoryEntities.OrderOutbox;

public interface IorderOutboxFactory {
    public OrderOutbox create(OrderId orderId, UserId userId, int cost, OrderStatus orderStatus);
}
