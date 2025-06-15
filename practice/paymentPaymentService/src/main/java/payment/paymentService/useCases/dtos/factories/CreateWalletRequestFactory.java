package payment.paymentService.useCases.dtos.factories;

import org.springframework.stereotype.Component;
import payment.paymentService.useCases.dtos.ifactories.IcreateWalletRequestFactory;
import payment.paymentService.useCases.dtos.requests.CreateWalletRequest;

@Component
public class CreateWalletRequestFactory implements IcreateWalletRequestFactory {
    @Override
    public CreateWalletRequest create(int userId) {
        return new CreateWalletRequest(userId);
    }
}
