package payment.paymentService.useCases.dtos.factories;

import org.springframework.stereotype.Component;
import payment.paymentService.useCases.dtos.ifactories.IgetWalletBalanceResponseFactory;
import payment.paymentService.useCases.dtos.responses.GetWalletBalanceResponse;

@Component
public class GetWalletBalanceResponseFactory implements IgetWalletBalanceResponseFactory {
    @Override
    public GetWalletBalanceResponse create(int amount) {
        return new GetWalletBalanceResponse(amount);
    }
}
