package payment.paymentService.useCases.dtos.ifactories;

import payment.paymentService.useCases.dtos.responses.OrderAppliementResponse;

public interface IorderAppliementResponseFactory {
    public OrderAppliementResponse create(int orderId, String status);
}
