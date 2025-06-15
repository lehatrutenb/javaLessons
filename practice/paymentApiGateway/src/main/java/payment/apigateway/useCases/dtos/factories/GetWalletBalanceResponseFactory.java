package payment.apigateway.useCases.dtos.factories;

import org.springframework.stereotype.Component;
import payment.apigateway.useCases.dtos.ifactories.IgetWalletBalanceResponseFactory;
import payment.apigateway.useCases.dtos.responses.GetWalletBalanceResponse;

@Component
public class GetWalletBalanceResponseFactory implements IgetWalletBalanceResponseFactory {
    @Override
    public GetWalletBalanceResponse create(int amount) {
        return new GetWalletBalanceResponse(amount);
    }
}
