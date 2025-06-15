package payment.apigateway.useCases.dtos.factories;

import org.springframework.stereotype.Component;
import payment.apigateway.useCases.dtos.ifactories.IcreateOrderRequestFactory;
import payment.apigateway.useCases.dtos.requests.CreateOrderRequest;

@Component
public class CreateOrderRequestFactory implements IcreateOrderRequestFactory {
    @Override
    public CreateOrderRequest create(int userId, int cost, String description) {
        return new CreateOrderRequest(userId, cost, description);
    }
}
