package payment.paymentService.adapters.ports.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import payment.paymentService.grpc.CreateWalletResponseProto;
import payment.paymentService.grpc.GetWalletBalanceResponseProto;
import payment.paymentService.grpc.TopUpWalletResponseProto;
import payment.paymentService.useCases.dtos.ifactories.IcreateWalletResponseFactory;
import payment.paymentService.useCases.dtos.ifactories.IgetWalletBalanceResponseFactory;
import payment.paymentService.useCases.dtos.ifactories.ItopUpWalletResponseFactory;
import payment.paymentService.useCases.dtos.responses.CreateWalletResponse;
import payment.paymentService.useCases.dtos.responses.GetWalletBalanceResponse;
import payment.paymentService.useCases.dtos.responses.OrderAppliementResponse;
import payment.paymentService.useCases.dtos.responses.TopUpWalletResponse;
import payments.paymentService.grpc.OrderCreationEventResponseProto;

@Component
@RequiredArgsConstructor
public class ResponseProtoMapper {
    private final SharedProtoMapper mapper;
    private final IcreateWalletResponseFactory createWalletResponseFactory;
    private final IgetWalletBalanceResponseFactory getWalletBalanceResponseFactory;
    private final ItopUpWalletResponseFactory topUpWalletResponseFactory;

    public CreateWalletResponseProto map(CreateWalletResponse response) {
        return CreateWalletResponseProto.newBuilder()
                .setWalletId(mapper.mapWalletId(response.walletId()))
                .build();
    }

    public GetWalletBalanceResponseProto map(GetWalletBalanceResponse response) {
        return GetWalletBalanceResponseProto.newBuilder()
                .setAmount(response.amount())
                .build();
    }

    public TopUpWalletResponseProto map(TopUpWalletResponse response) {
        return TopUpWalletResponseProto.newBuilder().build();
    }

    public OrderCreationEventResponseProto map(OrderAppliementResponse response) {
        return OrderCreationEventResponseProto.newBuilder()
                .setOrderId(mapper.mapOrderId(response.orderId()))
                .setOrderStatus(mapper.map(response.status()))
                .build();
    }
}
