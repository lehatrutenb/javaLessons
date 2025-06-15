package payment.paymentService.core.domainServices.iFactories;

import payment.paymentService.core.domainServices.holders.IorderIdHolder;
import payment.paymentService.core.entities.OrderId;

public interface IorderIdFactory {
    public OrderId create(int orderId);
    public OrderId create(IorderIdHolder orderIdHolder);
}
