package payment.orderService.useCases.dtos.ifactories;

import payment.orderService.useCases.dtos.responses.OrderCreationEventResponse;

public interface IorderCreationEventResponseFactory {
    public OrderCreationEventResponse create(int orderId, String status);
}
