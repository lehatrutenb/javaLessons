package payment.paymentService.useCases.dtos.ifactories;

import payment.paymentService.useCases.dtos.requests.GetWalletBalanceRequest;

public interface IgetWalletBalanceRequestFactory {
    public GetWalletBalanceRequest create(int walletId);
}
