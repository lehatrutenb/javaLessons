package payment.apigateway.useCases.dtos.ifactories;

import payment.apigateway.useCases.dtos.responses.CreateWalletResponse;

public interface IcreateWalletResponseFactory {
    public CreateWalletResponse create(int walletId);
}
