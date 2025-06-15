package payment.apigateway.useCases.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.requests.CreateWalletRequest;
import payment.apigateway.useCases.dtos.requests.TopUpWalletRequest;
import payment.apigateway.useCases.dtos.responses.CreateWalletResponse;
import payment.apigateway.useCases.dtos.responses.TopUpWalletResponse;
import payment.apigateway.useCases.iShared.IcreateWallet;
import payment.apigateway.useCases.iShared.ItopUpWallet;
import payment.apigateway.useCases.iShared.outPorts.IcreateWalletOutPort;
import payment.apigateway.useCases.iShared.outPorts.ItopUpWalletOutPort;
import payment.apigateway.useCases.wrappers.ErrorWrapper;
import payment.apigateway.useCases.wrappers.WrappedObject;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class TopUpWalletCommand extends WrappedObject<TopUpWalletRequest, Optional<TopUpWalletResponse>> implements ItopUpWallet {
    @Autowired
    private ErrorWrapper<TopUpWalletRequest, Optional<TopUpWalletResponse>> errorWrapper;

    @Autowired
    private ItopUpWalletOutPort topUpWalletOutPort;

    @PostConstruct
    private void configWrappers() {
        this.setWrappers(errorWrapper);
    }

    @Override
    public
    Optional<TopUpWalletResponse> endHandle(TopUpWalletRequest req, WrappedError error) {
        return topUpWalletOutPort.topUp(req, error);
    }
}
