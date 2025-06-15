package payment.paymentService.useCases.dtos.ifactories;

import payment.paymentService.useCases.dtos.requests.TopUpWalletRequest;

public interface ItopUpWalletRequestFactory {
    public TopUpWalletRequest create(int walletId, int amount);
}
