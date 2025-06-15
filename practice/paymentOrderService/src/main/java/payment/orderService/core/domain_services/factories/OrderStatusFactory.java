package payment.orderService.core.domain_services.factories;

import org.springframework.stereotype.Component;
import payment.orderService.core.domain_services.ifactories.IorderStatusFactory;
import payment.orderService.core.entities.OrderStatus;

import java.util.Optional;

@Component
public class OrderStatusFactory implements IorderStatusFactory {
    @Override
    public Optional<OrderStatus> create(String status) {
        return OrderStatus.get(status);
    }
}
