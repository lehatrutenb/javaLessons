package payment.apigateway.useCases.iShared.outPorts;

import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.requests.CreateOrderRequest;
import payment.apigateway.useCases.dtos.responses.CreateOrderResponse;

import java.util.Optional;

public interface IcreateOrderOutPort {
    public Optional<CreateOrderResponse> createOrder(CreateOrderRequest request, WrappedError err);
}
