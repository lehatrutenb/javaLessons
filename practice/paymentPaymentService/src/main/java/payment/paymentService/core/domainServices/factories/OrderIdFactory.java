package payment.paymentService.core.domainServices.factories;

import org.springframework.stereotype.Component;
import payment.paymentService.core.domainServices.holders.IorderIdHolder;
import payment.paymentService.core.domainServices.iFactories.IorderIdFactory;
import payment.paymentService.core.entities.OrderId;

@Component
public class OrderIdFactory implements IorderIdFactory {
    @Override
    public OrderId create(int orderId) {
        return new OrderId(orderId);
    }

    @Override
    public OrderId create(IorderIdHolder orderIdHolder) {
        return new OrderId(orderIdHolder.orderId());
    }
}
