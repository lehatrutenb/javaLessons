package payment.orderService.useCases.dtos.factories;

import org.springframework.stereotype.Component;
import payment.orderService.useCases.dtos.ifactories.IorderCreationEventResponseFactory;
import payment.orderService.useCases.dtos.responses.OrderCreationEventResponse;

@Component
public class OrderCreationEventResponseFactory implements IorderCreationEventResponseFactory {
    public OrderCreationEventResponse create(int orderId, String status) {
        return new OrderCreationEventResponse(orderId, status);
    }
}
