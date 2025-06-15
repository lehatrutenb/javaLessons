package payment.paymentService.useCases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import payment.paymentService.core.domainServices.iFactories.IorderIdFactory;
import payment.paymentService.core.domainServices.iFactories.IorderStatusFactory;
import payment.paymentService.core.domainServices.iFactories.IuserIdFactory;
import payment.paymentService.core.domainServices.iFactories.IwalletIdFactory;
import payment.paymentService.useCases.dtos.requests.OrderAppliementRequest;
import payment.paymentService.useCases.iShared.Ifacade;
import payment.paymentService.shared.WrappedError;
import payment.paymentService.useCases.dtos.ifactories.IcreateWalletResponseFactory;
import payment.paymentService.useCases.dtos.ifactories.IgetWalletBalanceResponseFactory;
import payment.paymentService.useCases.dtos.ifactories.ItopUpWalletResponseFactory;
import payment.paymentService.useCases.dtos.requests.CreateWalletRequest;
import payment.paymentService.useCases.dtos.requests.GetWalletBalanceRequest;
import payment.paymentService.useCases.dtos.requests.TopUpWalletRequest;
import payment.paymentService.useCases.dtos.responses.CreateWalletResponse;
import payment.paymentService.useCases.dtos.responses.GetWalletBalanceResponse;
import payment.paymentService.useCases.dtos.responses.TopUpWalletResponse;
import payment.paymentService.useCases.iShared.IorderService;
import payment.paymentService.useCases.iShared.IpaymentService;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class Facade implements Ifacade {
    private final IpaymentService paymentService;
    private final IuserIdFactory userIdFactory;
    private final IwalletIdFactory walletIdFactory;
    private final IorderIdFactory orderIdFactory;
    private final IcreateWalletResponseFactory createWalletResponseFactory;
    private final IgetWalletBalanceResponseFactory getWalletBalanceResponseFactory;
    private final ItopUpWalletResponseFactory topUpWalletResponseFactory;
    private final IorderService orderService;
    private final IorderStatusFactory orderStatusFactory;

    private void unexpectedError(WrappedError error) {
        error.setCode(500);
        error.setMessage("Got unexpected server error");
    }

    @Override
    public Optional<CreateWalletResponse> createWallet(CreateWalletRequest request, WrappedError error) {
        try {
            return paymentService.createWallet(userIdFactory.create(request), error).map(createWalletResponseFactory::create);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            unexpectedError(error);
            return Optional.empty();
        }
    }

    @Override
    public Optional<GetWalletBalanceResponse> getWalletBalance(GetWalletBalanceRequest request, WrappedError error) {
        try {
            return paymentService.getWalletBalance(walletIdFactory.create(request), error).map(getWalletBalanceResponseFactory::create);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            unexpectedError(error);
            return Optional.empty();
        }
    }

    @Override
    public Optional<TopUpWalletResponse> topUpWallet(TopUpWalletRequest request, WrappedError error) {
        try {
            paymentService.topUpWallet(walletIdFactory.create(request), request.amount(), error);
            return Optional.of(topUpWalletResponseFactory.create());
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            unexpectedError(error);
            return Optional.empty();
        }
    }

    @Override
    public void processOrderAppliementRequest(OrderAppliementRequest request, WrappedError error) {
        try {
            orderService.applyOrder(
                    orderIdFactory.create(request),
                    userIdFactory.create(request),
                    request.cost(),
                    orderStatusFactory.create(request),
                    error);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            unexpectedError(error);
        }
    }
}
