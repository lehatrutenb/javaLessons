package payment.apigateway.shared;

import org.springframework.stereotype.Component;
import payment.apigateway.Ishared.IwrappedErrorFactory;

@Component
public class WrappedErrorFactory implements IwrappedErrorFactory {
    public WrappedError create() {
        return new WrappedError();
    }

    public WrappedError create(int code, String message) {
        return new WrappedError(code, message);
    }
}
