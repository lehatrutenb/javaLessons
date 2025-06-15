package payment.apigateway.useCases.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.requests.GetOrderMetaRequest;
import payment.apigateway.useCases.dtos.responses.GetOrderMetaResponse;
import payment.apigateway.useCases.iShared.IgetOrderMeta;
import payment.apigateway.useCases.iShared.outPorts.IgetOrderMetaOutPort;
import payment.apigateway.useCases.wrappers.ErrorWrapper;
import payment.apigateway.useCases.wrappers.WrappedObject;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class GetOrderMetaQuery extends WrappedObject<GetOrderMetaRequest, Optional<GetOrderMetaResponse>> implements IgetOrderMeta {
    @Autowired
    private ErrorWrapper<GetOrderMetaRequest, Optional<GetOrderMetaResponse>> errorWrapper;

    @Autowired
    private IgetOrderMetaOutPort getOrderMetaOutPort;

    @Override
    public Optional<GetOrderMetaResponse> endHandle(GetOrderMetaRequest req, WrappedError error) {
        return getOrderMetaOutPort.getOrderMeta(req, error);
    }

    @PostConstruct
    private void configWrappers() {
        this.setWrappers(errorWrapper);
    }
}