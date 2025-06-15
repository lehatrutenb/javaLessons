package payment.apigateway.adapters.outPorts.grpc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import payment.apigateway.adapters.outPorts.grpc.mappers.PaymentServiceMapper;
import payment.apigateway.adapters.outPorts.grpc.mappers.WrappedErrorMapper;
import payment.apigateway.grpc.PaymentServiceGrpc;
import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.requests.CreateWalletRequest;
import payment.apigateway.useCases.dtos.requests.GetWalletBalanceRequest;
import payment.apigateway.useCases.dtos.requests.TopUpWalletRequest;
import payment.apigateway.useCases.dtos.responses.CreateWalletResponse;
import payment.apigateway.useCases.dtos.responses.GetWalletBalanceResponse;
import payment.apigateway.useCases.dtos.responses.TopUpWalletResponse;
import payment.apigateway.useCases.iShared.outPorts.IpaymentServiceOutController;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PaymentServiceOutController implements IpaymentServiceOutController {
    private final PaymentServiceMapper paymentServiceMapper;
    private final PaymentServiceGrpc.PaymentServiceBlockingStub paymentServiceBlockingStub;
    private final WrappedErrorMapper wrappedErrorMapper;

    @Override
    public Optional<CreateWalletResponse> createWallet(CreateWalletRequest request, WrappedError err) {
        var response = paymentServiceBlockingStub.createWallet(
                paymentServiceMapper.map(request)
        );

        if (response.hasError()) {
            wrappedErrorMapper.map(response.getError(), err);
        }
        return paymentServiceMapper.map(response);
    }

    @Override
    public Optional<GetWalletBalanceResponse> getWalletBalance(GetWalletBalanceRequest request, WrappedError err) {
        var response = paymentServiceBlockingStub.getWalletBalance(
                paymentServiceMapper.map(request)
        );

        if (response.hasError()) {
            wrappedErrorMapper.map(response.getError(), err);
        }
        return paymentServiceMapper.map(response);
    }

    @Override
    public Optional<TopUpWalletResponse> topUp(TopUpWalletRequest request, WrappedError err) {
        var response = paymentServiceBlockingStub.topUpWallet(
                paymentServiceMapper.map(request)
        );

        if (response.hasError()) {
            wrappedErrorMapper.map(response.getError(), err);
        }
        return paymentServiceMapper.map(response);
    }
}
