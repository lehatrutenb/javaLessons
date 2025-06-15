package payment.orderService.useCases.dtos.factories;

import org.springframework.stereotype.Component;
import payment.orderService.useCases.dtos.ifactories.IorderAppliementRequestFactory;
import payment.orderService.useCases.dtos.requests.OrderAppliementRequest;

@Component
public class OrderAppliementRequestFactory implements IorderAppliementRequestFactory {
    @Override
    public OrderAppliementRequest create(int orderId, String status) {
        return new OrderAppliementRequest(orderId, status);
    }
}
