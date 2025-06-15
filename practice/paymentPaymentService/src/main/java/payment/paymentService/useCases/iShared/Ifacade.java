package payment.paymentService.useCases.iShared;

import payment.paymentService.shared.WrappedError;
import payment.paymentService.useCases.dtos.requests.CreateWalletRequest;
import payment.paymentService.useCases.dtos.requests.GetWalletBalanceRequest;
import payment.paymentService.useCases.dtos.requests.OrderAppliementRequest;
import payment.paymentService.useCases.dtos.requests.TopUpWalletRequest;
import payment.paymentService.useCases.dtos.responses.CreateWalletResponse;
import payment.paymentService.useCases.dtos.responses.GetWalletBalanceResponse;
import payment.paymentService.useCases.dtos.responses.TopUpWalletResponse;

import java.util.Optional;

public interface Ifacade {
    public Optional<CreateWalletResponse> createWallet(CreateWalletRequest request, WrappedError error);
    public Optional<GetWalletBalanceResponse> getWalletBalance(GetWalletBalanceRequest request, WrappedError error);
    public Optional<TopUpWalletResponse> topUpWallet(TopUpWalletRequest request, WrappedError error);
    public void processOrderAppliementRequest(OrderAppliementRequest request, WrappedError error);
}
