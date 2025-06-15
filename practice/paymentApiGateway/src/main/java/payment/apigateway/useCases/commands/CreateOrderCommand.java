package payment.apigateway.useCases.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.iShared.IcreateOrder;
import payment.apigateway.useCases.wrappers.WrappedObject;
import payment.apigateway.useCases.dtos.requests.CreateOrderRequest;
import payment.apigateway.useCases.dtos.responses.CreateOrderResponse;
import payment.apigateway.useCases.wrappers.ErrorWrapper;
import payment.apigateway.useCases.iShared.outPorts.IcreateOrderOutPort;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class CreateOrderCommand extends WrappedObject<CreateOrderRequest, Optional<CreateOrderResponse>> implements IcreateOrder {
    @Autowired
    private ErrorWrapper<CreateOrderRequest, Optional<CreateOrderResponse>> errorWrapper;

    @Autowired
    private IcreateOrderOutPort createOrderOutPort;

    @Override
    public Optional<CreateOrderResponse> endHandle(CreateOrderRequest req, WrappedError error) {
        return createOrderOutPort.createOrder(req, error);
    }

    @PostConstruct
    private void configWrappers() {
        this.setWrappers(errorWrapper);
    }
}
