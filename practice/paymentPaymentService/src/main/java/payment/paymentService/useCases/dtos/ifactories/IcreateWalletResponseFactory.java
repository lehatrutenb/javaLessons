package payment.paymentService.useCases.dtos.ifactories;

import payment.paymentService.core.entities.WalletId;
import payment.paymentService.useCases.dtos.responses.CreateWalletResponse;

public interface IcreateWalletResponseFactory {
    public CreateWalletResponse create(WalletId walletId);
}
