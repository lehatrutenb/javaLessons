package payment.apigateway.adapters.outPorts.grpc.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import payment.apigateway.Ishared.IwrappedErrorFactory;
import payment.apigateway.grpc.ErrorProto;
import payment.apigateway.shared.WrappedError;

@Component
@RequiredArgsConstructor
public class WrappedErrorMapper {
    private final IwrappedErrorFactory wrappedErrorFactory;

    public void map(ErrorProto errorProto, WrappedError error) {
        error.setCode(errorProto.getCode());
        error.setMessage(errorProto.getMessage());
    }
}
