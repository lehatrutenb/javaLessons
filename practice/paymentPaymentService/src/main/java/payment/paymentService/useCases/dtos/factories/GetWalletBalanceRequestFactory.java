package payment.paymentService.useCases.dtos.factories;

import org.springframework.stereotype.Component;
import payment.paymentService.useCases.dtos.ifactories.IgetWalletBalanceRequestFactory;
import payment.paymentService.useCases.dtos.requests.GetWalletBalanceRequest;

@Component
public class GetWalletBalanceRequestFactory implements IgetWalletBalanceRequestFactory {
    @Override
    public GetWalletBalanceRequest create(int walletId) {
        return new GetWalletBalanceRequest(walletId);
    }
}
