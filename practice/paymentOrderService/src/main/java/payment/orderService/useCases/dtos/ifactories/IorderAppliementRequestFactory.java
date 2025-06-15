package payment.orderService.useCases.dtos.ifactories;

import payment.orderService.useCases.dtos.requests.OrderAppliementRequest;

public interface IorderAppliementRequestFactory {
    public OrderAppliementRequest create(int orderId, String status);
}
