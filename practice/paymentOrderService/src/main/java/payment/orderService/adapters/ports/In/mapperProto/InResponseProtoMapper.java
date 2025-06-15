package payment.orderService.adapters.ports.In.mapperProto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import payment.orderService.adapters.ports.shared.SharedResponseProtoMapper;
import payment.orderService.grpc.*;
import payment.orderService.shared.WrappedError;
import payment.orderService.useCases.dtos.responses.CreateOrderResponse;
import payment.orderService.useCases.dtos.responses.GetOrderListResponse;
import payment.orderService.useCases.dtos.responses.GetOrderMetaResponse;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InResponseProtoMapper {
    private final SharedResponseProtoMapper sharedMapper;

    public WrappedCreateOrderResponseProto mapCreateOrder(Optional<CreateOrderResponse> response, WrappedError error) {
        if (response.isEmpty()) {
            return WrappedCreateOrderResponseProto.newBuilder()
                    .setError(sharedMapper.map(error))
                    .build();
        }
        return WrappedCreateOrderResponseProto.newBuilder()
                .setCreateOrderResponse(
                        CreateOrderResponseProto.newBuilder()
                                .setOrderId(sharedMapper.mapOrderId(response.get().orderId()))
                                .build()
                )
                .build();
    }

    public WrappedGetOrderMetaResponseProto mapGetOrderMeta(Optional<GetOrderMetaResponse> response, WrappedError error) {
        if (response.isEmpty()) {
            return WrappedGetOrderMetaResponseProto.newBuilder()
                    .setError(sharedMapper.map(error))
                    .build();
        }
        return WrappedGetOrderMetaResponseProto.newBuilder()
                .setGetOrderMetaResponse(
                        GetOrderMetaResponseProto.newBuilder()
                                .setOrderStatus(sharedMapper.map(response.get().orderStatus()))
                                .build()
                )
                .build();
    }

    public WrappedGetOrderListResponseProto mapGetOrderList(Optional<GetOrderListResponse> response, WrappedError error) {
        if (response.isEmpty()) {
            return WrappedGetOrderListResponseProto.newBuilder()
                    .setError(sharedMapper.map(error))
                    .build();
        }
        var responseProto = GetOrderListResponseProto.newBuilder();
        response.get().ids().forEach(id -> responseProto.addOrderIds(sharedMapper.mapOrderId(id)));
        return WrappedGetOrderListResponseProto.newBuilder()
                .setGetOrderListResponseProto(responseProto.build())
                .build();
    }
}
