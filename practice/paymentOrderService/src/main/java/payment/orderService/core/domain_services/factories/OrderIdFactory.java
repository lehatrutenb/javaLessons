package payment.orderService.core.domain_services.factories;

import org.springframework.stereotype.Component;
import payment.orderService.core.domain_services.ifactories.IorderIdFactory;
import payment.orderService.core.entities.OrderId;

@Component
public class OrderIdFactory implements IorderIdFactory {
    public OrderId create(int id) {
        return new OrderId(id);
    }
}
