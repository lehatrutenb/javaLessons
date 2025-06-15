package payment.apigateway.useCases.dtos.ifactories;

import payment.apigateway.useCases.dtos.responses.GetWalletBalanceResponse;

public interface IgetWalletBalanceResponseFactory {
    public GetWalletBalanceResponse create(int amount);
}
