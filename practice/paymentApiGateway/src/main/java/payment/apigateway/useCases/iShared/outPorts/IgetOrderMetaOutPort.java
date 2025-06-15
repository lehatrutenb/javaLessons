package payment.apigateway.useCases.iShared.outPorts;

import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.requests.GetOrderMetaRequest;
import payment.apigateway.useCases.dtos.responses.GetOrderMetaResponse;

import java.util.Optional;

public interface IgetOrderMetaOutPort {
    public Optional<GetOrderMetaResponse> getOrderMeta(GetOrderMetaRequest request, WrappedError error);
}
