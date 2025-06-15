package payment.paymentService.useCases.dtos.factories;

import org.springframework.stereotype.Component;
import payment.paymentService.core.entities.WalletId;
import payment.paymentService.useCases.dtos.ifactories.IcreateWalletResponseFactory;
import payment.paymentService.useCases.dtos.responses.CreateWalletResponse;

@Component
public class CreateWalletResponseFactory implements IcreateWalletResponseFactory {
    @Override
    public CreateWalletResponse create(WalletId walletId) {
        return new CreateWalletResponse(walletId.getId());
    }
}
