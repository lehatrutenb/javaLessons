package payment.paymentService.adapters.ports.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import payment.paymentService.grpc.CreateWalletRequestProto;
import payment.paymentService.grpc.GetWalletBalanceRequestProto;
import payment.paymentService.grpc.TopUpWalletRequestProto;
import payment.paymentService.useCases.dtos.ifactories.IcreateWalletRequestFactory;
import payment.paymentService.useCases.dtos.ifactories.IgetWalletBalanceRequestFactory;
import payment.paymentService.useCases.dtos.ifactories.IorderAppliementRequestFactory;
import payment.paymentService.useCases.dtos.ifactories.ItopUpWalletRequestFactory;
import payment.paymentService.useCases.dtos.requests.CreateWalletRequest;
import payment.paymentService.useCases.dtos.requests.GetWalletBalanceRequest;
import payment.paymentService.useCases.dtos.requests.OrderAppliementRequest;
import payment.paymentService.useCases.dtos.requests.TopUpWalletRequest;
import payments.paymentService.grpc.OrderCreationEventRequestProto;

@Component
@RequiredArgsConstructor
public class RequestProtoMapper {
    private final SharedProtoMapper mapper;
    private final IcreateWalletRequestFactory createWalletRequestFactory;
    private final IgetWalletBalanceRequestFactory getWalletBalanceRequestFactory;
    private final ItopUpWalletRequestFactory topUpWalletRequestFactory;
    private final IorderAppliementRequestFactory orderAppliementRequestFactory;

    public CreateWalletRequest map(CreateWalletRequestProto request) {
        return createWalletRequestFactory.create(mapper.map(request.getUserId()));
    }

    public GetWalletBalanceRequest map(GetWalletBalanceRequestProto request) {
        return getWalletBalanceRequestFactory.create(mapper.map(request.getWalletId()));
    }

    public TopUpWalletRequest map(TopUpWalletRequestProto request) {
        return topUpWalletRequestFactory.create(
                mapper.map(request.getWalletId()), request.getAmount()
        );
    }

    public OrderAppliementRequest map(OrderCreationEventRequestProto request) {
        return orderAppliementRequestFactory.create(
                mapper.map(request.getOrderId()),
                mapper.map(request.getUserId()),
                request.getCost()
        );
    }
}
