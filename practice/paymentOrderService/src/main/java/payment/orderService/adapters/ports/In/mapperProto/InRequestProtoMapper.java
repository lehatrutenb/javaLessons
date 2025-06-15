package payment.orderService.adapters.ports.In.mapperProto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import payment.orderService.adapters.ports.shared.SharedRequestProtoMapper;
import payment.orderService.grpc.*;
import payment.orderService.useCases.dtos.ifactories.IorderAppliementRequestFactory;
import payment.orderService.useCases.dtos.ifactories.IorderRequestFactory;
import payment.orderService.useCases.dtos.requests.CreateOrderRequest;
import payment.orderService.useCases.dtos.requests.GetOrderListRequest;
import payment.orderService.useCases.dtos.requests.GetOrderMetaRequest;
import payment.orderService.useCases.dtos.requests.OrderAppliementRequest;
import payments.orderService.grpc.OrderCreationEventRequestProto;
import payments.orderService.grpc.OrderCreationEventResponseProto;

@Component
@RequiredArgsConstructor
public class InRequestProtoMapper {
    private final IorderRequestFactory oderRequestFactory;
    private final SharedRequestProtoMapper sharedMapper;
    private final IorderAppliementRequestFactory orderAppliementRequestFactory;

    public CreateOrderRequest map(CreateOrderRequestProto request) {
        return oderRequestFactory.create(
                sharedMapper.map(request.getUserId()),
                request.getCost(),
                sharedMapper.map(request.getOrderDescription())
        );
    }

    public GetOrderMetaRequest map(GetOrderMetaRequestProto request) {
        return oderRequestFactory.createGetOrderMeta(
                sharedMapper.map(request.getOrderId())
        );
    }

    public GetOrderListRequest map(GetOrderListRequestProto request) {
        return oderRequestFactory.createGetOrderList(
                sharedMapper.map(request.getUserId())
        );
    }

    public OrderAppliementRequest map(OrderCreationEventResponseProto request) {
        return orderAppliementRequestFactory.create(
                sharedMapper.map(request.getOrderId()),
                sharedMapper.map(request.getOrderStatus())
        );
    }
}
