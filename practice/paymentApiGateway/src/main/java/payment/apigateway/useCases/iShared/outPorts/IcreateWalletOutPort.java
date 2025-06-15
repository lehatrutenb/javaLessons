package payment.apigateway.useCases.iShared.outPorts;

import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.requests.CreateWalletRequest;
import payment.apigateway.useCases.dtos.responses.CreateWalletResponse;

import java.util.Optional;

public interface IcreateWalletOutPort {
    public Optional<CreateWalletResponse> createWallet(CreateWalletRequest request, WrappedError err);
}