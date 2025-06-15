package payment.paymentService.core.domainServices.factories;

import org.springframework.stereotype.Component;
import payment.paymentService.core.domainServices.holders.IorderStatusHolder;
import payment.paymentService.core.domainServices.iFactories.IorderStatusFactory;
import payment.paymentService.core.entities.OrderStatus;

@Component
public class OrderStatusFactory implements IorderStatusFactory {
    @Override
    public OrderStatus create(IorderStatusHolder holder) {
        return OrderStatus.valueOf(holder.status());
    }
}
