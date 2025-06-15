package payment.paymentService.shared;

import org.springframework.stereotype.Component;
import payment.paymentService.iShared.IwrappedErrorFactory;

@Component
public class WrappedErrorFactory implements IwrappedErrorFactory {
    @Override
    public WrappedError create(int code, String message) {
        return new WrappedError(code, message);
    }

    @Override
    public WrappedError create() {
        return new WrappedError();
    }
}
