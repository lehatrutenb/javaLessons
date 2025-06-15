package payment.apigateway.useCases.iShared.outPorts;

import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.requests.TopUpWalletRequest;
import payment.apigateway.useCases.dtos.responses.TopUpWalletResponse;

import java.util.Optional;

public interface ItopUpWalletOutPort {
    public Optional<TopUpWalletResponse> topUp(TopUpWalletRequest request, WrappedError error);
}
