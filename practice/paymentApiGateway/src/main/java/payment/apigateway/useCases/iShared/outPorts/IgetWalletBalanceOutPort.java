package payment.apigateway.useCases.iShared.outPorts;

import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.requests.GetWalletBalanceRequest;
import payment.apigateway.useCases.dtos.responses.GetWalletBalanceResponse;

import java.util.Optional;

public interface IgetWalletBalanceOutPort {
    public Optional<GetWalletBalanceResponse> getWalletBalance(GetWalletBalanceRequest request, WrappedError error);
}