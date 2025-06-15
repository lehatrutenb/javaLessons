package payment.apigateway.useCases.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.requests.GetWalletBalanceRequest;
import payment.apigateway.useCases.dtos.responses.GetWalletBalanceResponse;
import payment.apigateway.useCases.iShared.IgetWalletBalance;
import payment.apigateway.useCases.iShared.outPorts.IgetWalletBalanceOutPort;
import payment.apigateway.useCases.wrappers.ErrorWrapper;
import payment.apigateway.useCases.wrappers.WrappedObject;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class GetWalletBalanceQuery extends WrappedObject<GetWalletBalanceRequest, Optional<GetWalletBalanceResponse>> implements IgetWalletBalance {
    @Autowired
    private IgetWalletBalanceOutPort getWalletBalanceOutPort;

    @Autowired
    private ErrorWrapper<GetWalletBalanceRequest, Optional<GetWalletBalanceResponse>> errorWrapper;

    @Override
    public Optional<GetWalletBalanceResponse> endHandle(GetWalletBalanceRequest req, WrappedError error) {
        return getWalletBalanceOutPort.getWalletBalance(req, error);
    }

    @PostConstruct
    private void configWrappers() {
        this.setWrappers(errorWrapper);
    }
}
