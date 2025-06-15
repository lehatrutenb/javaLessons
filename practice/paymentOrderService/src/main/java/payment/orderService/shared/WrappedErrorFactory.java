package payment.orderService.shared;

import org.springframework.stereotype.Component;
import payment.orderService.ishared.IwrappedErrorFactory;

@Component
public class WrappedErrorFactory implements IwrappedErrorFactory {
    public WrappedError create() {
        return new WrappedError();
    }

    public WrappedError create(int code, String message) {
        return new WrappedError(code, message);
    }
}
