package payment.apigateway.useCases.iShared;

import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.requests.TopUpWalletRequest;
import payment.apigateway.useCases.dtos.responses.TopUpWalletResponse;

import java.util.Optional;

public interface ItopUpWallet {
    public Optional<TopUpWalletResponse> endHandle(TopUpWalletRequest request, WrappedError error);
}
