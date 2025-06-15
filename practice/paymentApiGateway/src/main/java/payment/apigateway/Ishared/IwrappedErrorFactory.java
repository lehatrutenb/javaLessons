package payment.apigateway.Ishared;

import payment.apigateway.shared.WrappedError;

public interface IwrappedErrorFactory {
    public WrappedError create();
    public WrappedError create(int code, String message);
}
