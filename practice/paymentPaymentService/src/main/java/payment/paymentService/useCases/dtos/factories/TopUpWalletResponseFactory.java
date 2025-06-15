package payment.paymentService.useCases.dtos.factories;

import org.springframework.stereotype.Component;
import payment.paymentService.useCases.dtos.ifactories.ItopUpWalletResponseFactory;
import payment.paymentService.useCases.dtos.responses.TopUpWalletResponse;

@Component
public class TopUpWalletResponseFactory implements ItopUpWalletResponseFactory {
    @Override
    public TopUpWalletResponse create() {
        return new TopUpWalletResponse();
    }
}
