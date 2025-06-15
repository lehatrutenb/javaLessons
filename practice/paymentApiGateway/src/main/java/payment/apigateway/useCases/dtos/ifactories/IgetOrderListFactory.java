package payment.apigateway.useCases.dtos.ifactories;

import payment.apigateway.useCases.dtos.requests.GetOrderListRequest;

public interface IgetOrderListFactory {
    public GetOrderListRequest create(int userId);
}
