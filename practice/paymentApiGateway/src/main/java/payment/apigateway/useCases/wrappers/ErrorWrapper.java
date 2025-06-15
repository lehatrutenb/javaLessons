package payment.apigateway.useCases.wrappers;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import payment.apigateway.shared.WrappedError;

@Service
@NoArgsConstructor
public class ErrorWrapper<In, Out> implements Iwrapper<In, Out> {
    @Override
    public void before(In in, WrappedError error) {

    }

    @Override
    public void after(Out out, WrappedError error) {

    }
}