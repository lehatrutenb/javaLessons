package payment.apigateway.useCases.dtos.factories;

import org.springframework.stereotype.Component;
import payment.apigateway.useCases.dtos.ifactories.IgetOrderListFactory;
import payment.apigateway.useCases.dtos.requests.GetOrderListRequest;

@Component
public class GetOrderListFactory implements IgetOrderListFactory {
    @Override
    public GetOrderListRequest create(int userId) {
        return new GetOrderListRequest(userId);
    }
}
