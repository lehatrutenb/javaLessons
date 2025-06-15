package payment.paymentService.useCases.dtos.factories;

import org.springframework.stereotype.Component;
import payment.paymentService.useCases.dtos.ifactories.IorderAppliementResponseFactory;
import payment.paymentService.useCases.dtos.responses.OrderAppliementResponse;

@Component
public class OrderAppliementResponseFactory implements IorderAppliementResponseFactory {
    @Override
    public OrderAppliementResponse create(int orderId, String status) {
        return new OrderAppliementResponse(orderId, status);
    }
}
