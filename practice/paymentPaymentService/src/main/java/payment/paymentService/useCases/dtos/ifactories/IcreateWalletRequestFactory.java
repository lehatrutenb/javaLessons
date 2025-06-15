package payment.paymentService.useCases.dtos.ifactories;

import payment.paymentService.useCases.dtos.requests.CreateWalletRequest;

public interface IcreateWalletRequestFactory {
    public CreateWalletRequest create(int userId);
}
