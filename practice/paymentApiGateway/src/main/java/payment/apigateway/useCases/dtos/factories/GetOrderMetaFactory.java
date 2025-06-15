package payment.apigateway.useCases.dtos.factories;

import org.springframework.stereotype.Component;
import payment.apigateway.useCases.dtos.ifactories.IgetOrderMetaFactory;
import payment.apigateway.useCases.dtos.requests.GetOrderMetaRequest;
import payment.apigateway.useCases.iShared.IgetOrderMeta;

@Component
public class GetOrderMetaFactory implements IgetOrderMetaFactory {
    @Override
    public GetOrderMetaRequest create(int orderId) {
        return new GetOrderMetaRequest(orderId);
    }
}
