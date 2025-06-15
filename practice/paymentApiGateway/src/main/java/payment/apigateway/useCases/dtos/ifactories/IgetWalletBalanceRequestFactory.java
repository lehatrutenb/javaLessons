package payment.apigateway.useCases.dtos.ifactories;

import payment.apigateway.useCases.dtos.requests.GetWalletBalanceRequest;

public interface IgetWalletBalanceRequestFactory {
    public GetWalletBalanceRequest create(int walletId);
}
