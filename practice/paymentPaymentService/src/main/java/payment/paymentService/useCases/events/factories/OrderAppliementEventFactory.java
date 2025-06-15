package payment.paymentService.useCases.events.factories;

import org.springframework.stereotype.Component;
import payment.paymentService.core.entities.Order;
import payment.paymentService.useCases.events.OrderAppliementEvent;

@Component
public class OrderAppliementEventFactory {
    public OrderAppliementEvent create(Order order) {
        return new OrderAppliementEvent(order.getId(), order.getUserId(), order.getCost(), order.getStatus());
    }
}

