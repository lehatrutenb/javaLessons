package payment.apigateway.useCases.dtos.factories;

import org.springframework.stereotype.Component;
import payment.apigateway.useCases.dtos.ifactories.ItopUpWalletResponseFactory;
import payment.apigateway.useCases.dtos.responses.TopUpWalletResponse;

@Component
public class TopUpWalletResponseFactory implements ItopUpWalletResponseFactory {
    @Override
    public TopUpWalletResponse create() {
        return new TopUpWalletResponse();
    }
}
