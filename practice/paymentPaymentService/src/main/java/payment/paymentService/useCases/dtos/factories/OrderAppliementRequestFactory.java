package payment.paymentService.useCases.dtos.factories;

import org.springframework.stereotype.Component;
import payment.paymentService.useCases.dtos.ifactories.IorderAppliementRequestFactory;
import payment.paymentService.useCases.dtos.requests.OrderAppliementRequest;

@Component
public class OrderAppliementRequestFactory implements IorderAppliementRequestFactory {
    @Override
    public OrderAppliementRequest create(int orderId, int userId, int cost) {
        return new OrderAppliementRequest(orderId, userId, cost, "NEW");
    }
}
