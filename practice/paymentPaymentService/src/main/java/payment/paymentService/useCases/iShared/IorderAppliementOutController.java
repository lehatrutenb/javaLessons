package payment.paymentService.useCases.iShared;

import payment.paymentService.useCases.dtos.responses.OrderAppliementResponse;

public interface IorderAppliementOutController {
    public void orderAppliementSend(OrderAppliementResponse orderAppliementResponse);
}
