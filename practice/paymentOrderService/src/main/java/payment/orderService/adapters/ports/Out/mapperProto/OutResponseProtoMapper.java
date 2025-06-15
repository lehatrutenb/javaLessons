package payment.orderService.adapters.ports.Out.mapperProto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import payment.orderService.adapters.ports.shared.SharedRequestProtoMapper;
import payment.orderService.shared.WrappedError;
import payment.orderService.useCases.dtos.ifactories.IorderCreationEventResponseFactory;
import payment.orderService.useCases.dtos.responses.OrderCreationEventResponse;
import payments.orderService.grpc.WrappedOrderCreationEventResponseProto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OutResponseProtoMapper {
    private final SharedRequestProtoMapper sharedMapper;
    private final IorderCreationEventResponseFactory orderCreationEventResponseFactory;

    public Optional<OrderCreationEventResponse> map(WrappedOrderCreationEventResponseProto wrappedResponse, WrappedError error) {
        if (wrappedResponse.hasError()) {
            sharedMapper.map(wrappedResponse.getError(), error);
            return Optional.empty();
        }

        var response = wrappedResponse.getCreateOrderResponse();
        return Optional.of(
            orderCreationEventResponseFactory.create(sharedMapper.map(response.getOrderId()), response.getOrderStatus().name())
        );
    }
}
