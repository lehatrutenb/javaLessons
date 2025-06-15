package payment.apigateway.useCases.dtos.factories;

import org.springframework.stereotype.Component;
import payment.apigateway.useCases.dtos.ifactories.IgetWalletBalanceRequestFactory;
import payment.apigateway.useCases.dtos.requests.GetWalletBalanceRequest;

@Component
public class GetWalletBalanceRequestFactory implements IgetWalletBalanceRequestFactory {
    @Override
    public GetWalletBalanceRequest create(int walletId) {
        return new GetWalletBalanceRequest(walletId);
    }
}
