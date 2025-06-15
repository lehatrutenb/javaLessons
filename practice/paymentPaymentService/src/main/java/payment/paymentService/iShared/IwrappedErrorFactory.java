package payment.paymentService.iShared;

import payment.paymentService.shared.WrappedError;

public interface IwrappedErrorFactory {
    public WrappedError create(int code, String message);
    public WrappedError create();
}
