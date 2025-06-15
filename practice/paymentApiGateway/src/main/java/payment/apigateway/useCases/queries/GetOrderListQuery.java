package payment.apigateway.useCases.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.requests.GetOrderListRequest;
import payment.apigateway.useCases.dtos.responses.GetOrderListResponse;
import payment.apigateway.useCases.iShared.IgetOrderList;
import payment.apigateway.useCases.iShared.IgetOrderMeta;
import payment.apigateway.useCases.iShared.outPorts.IgetOrderListOutPort;
import payment.apigateway.useCases.wrappers.ErrorWrapper;
import payment.apigateway.useCases.wrappers.WrappedObject;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class GetOrderListQuery extends WrappedObject<GetOrderListRequest, Optional<GetOrderListResponse>> implements IgetOrderList {
    @Autowired
    private ErrorWrapper<GetOrderListRequest, Optional<GetOrderListResponse>> errorWrapper;

    @Autowired
    private IgetOrderListOutPort getOrderListOutPort;

    @Override
    public Optional<GetOrderListResponse> endHandle(GetOrderListRequest req, WrappedError error) {
        return getOrderListOutPort.getOrderList(req, error);
    }

    @PostConstruct
    private void configWrappers() {
        this.setWrappers(errorWrapper);
    }
}