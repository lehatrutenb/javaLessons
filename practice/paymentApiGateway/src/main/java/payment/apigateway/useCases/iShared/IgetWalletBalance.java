package payment.apigateway.useCases.iShared;

import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.requests.GetWalletBalanceRequest;
import payment.apigateway.useCases.dtos.responses.GetWalletBalanceResponse;
import java.util.Optional;

public interface IgetWalletBalance {
    public Optional<GetWalletBalanceResponse> endHandle(GetWalletBalanceRequest request, WrappedError error);
}
