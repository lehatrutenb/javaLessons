package payment.apigateway.useCases.iShared;

import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.requests.GetOrderMetaRequest;
import payment.apigateway.useCases.dtos.responses.GetOrderMetaResponse;

import java.util.Optional;

public interface IgetOrderMeta {
    public Optional<GetOrderMetaResponse> endHandle(GetOrderMetaRequest request, WrappedError error);
}
