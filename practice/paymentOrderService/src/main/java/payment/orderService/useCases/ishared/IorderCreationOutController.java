package payment.orderService.useCases.ishared;

import payment.orderService.useCases.dtos.requests.CreateOrderRequest;
import payment.orderService.useCases.events.OrderCreationEvent;

public interface IorderCreationOutController {
    public void handle(OrderCreationEvent request);
}
