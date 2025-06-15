package payment.paymentService.useCases.dtos.factories;

import org.springframework.stereotype.Component;
import payment.paymentService.useCases.dtos.ifactories.ItopUpWalletRequestFactory;
import payment.paymentService.useCases.dtos.requests.TopUpWalletRequest;

@Component
public class TopUpWalletRequestFactory implements ItopUpWalletRequestFactory {
    @Override
    public TopUpWalletRequest create(int walletId, int amount) {
        return new TopUpWalletRequest(walletId, amount);
    }
}
