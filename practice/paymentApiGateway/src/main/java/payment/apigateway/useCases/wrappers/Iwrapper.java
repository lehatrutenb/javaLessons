package payment.apigateway.useCases.wrappers;

import payment.apigateway.shared.WrappedError;

public interface Iwrapper<In, Out> {
    public void before(In in, WrappedError error);
    public void after(Out out, WrappedError error);
}
