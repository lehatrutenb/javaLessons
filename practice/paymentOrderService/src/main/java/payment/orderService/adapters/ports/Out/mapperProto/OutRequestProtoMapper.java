package payment.orderService.adapters.ports.Out.mapperProto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import payment.orderService.adapters.ports.shared.SharedRequestProtoMapper;
import payment.orderService.adapters.ports.shared.SharedResponseProtoMapper;
import payment.orderService.useCases.events.OrderCreationEvent;
import payments.orderService.grpc.OrderCreationEventRequestProto;

@Component
@RequiredArgsConstructor
public class OutRequestProtoMapper {
    private final SharedResponseProtoMapper sharedMapper;

    public OrderCreationEventRequestProto map(OrderCreationEvent request) {
        return OrderCreationEventRequestProto.newBuilder()
                .setUserId(sharedMapper.map(request.userId()))
                .setOrderId(sharedMapper.map(request.orderId()))
                .setCost(request.cost())
                .setOrderDescription(sharedMapper.map(request.description()))
                .build();
    }
}
