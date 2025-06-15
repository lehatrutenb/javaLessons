package payment.paymentService.useCases.dtos.ifactories;

import payment.paymentService.useCases.dtos.requests.OrderAppliementRequest;

public interface IorderAppliementRequestFactory {
    public OrderAppliementRequest create(int orderId, int userId, int cost);
}
