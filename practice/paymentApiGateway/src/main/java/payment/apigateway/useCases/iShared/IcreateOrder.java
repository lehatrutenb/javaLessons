package payment.apigateway.useCases.iShared;

import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.requests.CreateOrderRequest;
import payment.apigateway.useCases.dtos.responses.CreateOrderResponse;

import java.util.Optional;

public interface IcreateOrder {
    public Optional<CreateOrderResponse> endHandle(CreateOrderRequest request, WrappedError err);
}
