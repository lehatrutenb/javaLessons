package payment.apigateway.useCases.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.requests.CreateOrderRequest;
import payment.apigateway.useCases.dtos.requests.CreateWalletRequest;
import payment.apigateway.useCases.dtos.responses.CreateOrderResponse;
import payment.apigateway.useCases.dtos.responses.CreateWalletResponse;
import payment.apigateway.useCases.iShared.IcreateOrder;
import payment.apigateway.useCases.iShared.IcreateWallet;
import payment.apigateway.useCases.iShared.outPorts.IcreateOrderOutPort;
import payment.apigateway.useCases.iShared.outPorts.IcreateWalletOutPort;
import payment.apigateway.useCases.wrappers.ErrorWrapper;
import payment.apigateway.useCases.wrappers.WrappedObject;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class CreateWalletCommand extends WrappedObject<CreateWalletRequest, Optional<CreateWalletResponse>> implements IcreateWallet {
    @Autowired
    private ErrorWrapper<CreateWalletRequest, Optional<CreateWalletResponse>> errorWrapper;

    @Autowired
    private IcreateWalletOutPort createWalletOutPort;

    @PostConstruct
    private void configWrappers() {
        this.setWrappers(errorWrapper);
    }

    @Override
    public Optional<CreateWalletResponse> endHandle(CreateWalletRequest req, WrappedError err) {
        return createWalletOutPort.createWallet(req, err);
    }
}