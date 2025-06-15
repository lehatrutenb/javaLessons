package payment.orderService.useCases.ishared;

import payment.orderService.core.entities.Order;
import payment.orderService.shared.WrappedError;
import payment.orderService.useCases.dtos.requests.CreateOrderRequest;
import payment.orderService.useCases.events.OrderCreationEvent;

public interface IorderCreationHandler {
    public void handle(OrderCreationEvent request) throws RuntimeException;
}
