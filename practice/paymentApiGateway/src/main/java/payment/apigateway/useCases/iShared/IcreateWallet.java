package payment.apigateway.useCases.iShared;

import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.requests.CreateOrderRequest;
import payment.apigateway.useCases.dtos.requests.CreateWalletRequest;
import payment.apigateway.useCases.dtos.responses.CreateOrderResponse;
import payment.apigateway.useCases.dtos.responses.CreateWalletResponse;

import java.util.Optional;

public interface IcreateWallet {
    public Optional<CreateWalletResponse> endHandle(CreateWalletRequest request, WrappedError err);
}