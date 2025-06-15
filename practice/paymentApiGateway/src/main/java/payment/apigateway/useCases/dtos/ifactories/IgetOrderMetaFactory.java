package payment.apigateway.useCases.dtos.ifactories;

import payment.apigateway.useCases.dtos.requests.GetOrderMetaRequest;

public interface IgetOrderMetaFactory {
    public GetOrderMetaRequest create(int orderId);
}
