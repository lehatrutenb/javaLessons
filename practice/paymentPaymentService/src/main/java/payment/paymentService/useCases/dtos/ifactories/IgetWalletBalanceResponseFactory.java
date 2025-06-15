package payment.paymentService.useCases.dtos.ifactories;

import payment.paymentService.useCases.dtos.responses.GetWalletBalanceResponse;

public interface IgetWalletBalanceResponseFactory {
    public GetWalletBalanceResponse create(int amount);
}
