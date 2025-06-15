package payment.apigateway.useCases.dtos.factories;

import org.springframework.stereotype.Component;
import payment.apigateway.useCases.dtos.ifactories.IcreateWalletResponseFactory;
import payment.apigateway.useCases.dtos.responses.CreateWalletResponse;

@Component
public class CreateWalletResponseFactory implements IcreateWalletResponseFactory {
    @Override
    public CreateWalletResponse create(int walletId) {
        return new CreateWalletResponse(walletId);
    }
}
