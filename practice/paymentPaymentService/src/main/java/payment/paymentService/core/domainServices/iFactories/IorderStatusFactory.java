package payment.paymentService.core.domainServices.iFactories;

import payment.paymentService.core.domainServices.holders.IorderStatusHolder;
import payment.paymentService.core.entities.OrderStatus;

public interface IorderStatusFactory {
    public OrderStatus create(IorderStatusHolder holder);
}
