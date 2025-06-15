package payment.orderService.useCases.events.factories;

import org.springframework.stereotype.Component;
import payment.orderService.core.entities.Order;
import payment.orderService.useCases.events.OrderCreationEvent;

@Component
public class OrderCreationEventFactory {
    public OrderCreationEvent create(Order order) {
        return new OrderCreationEvent(order.getId(), order.getUserId(), order.getCost(), order.getDescription());
    }
}
