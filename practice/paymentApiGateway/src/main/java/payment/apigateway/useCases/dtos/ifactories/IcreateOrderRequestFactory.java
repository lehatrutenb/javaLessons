package payment.apigateway.useCases.dtos.ifactories;

import payment.apigateway.useCases.dtos.requests.CreateOrderRequest;

public interface IcreateOrderRequestFactory {
    public CreateOrderRequest create(int userId, int cost, String description);
}
