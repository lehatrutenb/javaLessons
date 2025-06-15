package payment.orderService.ishared;


import payment.orderService.shared.WrappedError;

public interface IwrappedErrorFactory {
    public WrappedError create();
    public WrappedError create(int code, String message);
}
