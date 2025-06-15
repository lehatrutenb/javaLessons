package payment.apigateway.adapters.outPorts.grpc.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import payment.apigateway.core.entities.OrderDescription;
import payment.apigateway.core.entities.OrderStatus;
import payment.apigateway.useCases.dtos.requests.CreateOrderRequest;
import payment.apigateway.useCases.dtos.requests.GetOrderListRequest;
import payment.apigateway.useCases.dtos.requests.GetOrderMetaRequest;
import payment.apigateway.useCases.dtos.responses.CreateOrderResponse;
import payment.apigateway.useCases.dtos.responses.GetOrderListResponse;
import payment.apigateway.useCases.dtos.responses.GetOrderMetaResponse;
import payment.apigateway.grpc.*;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderServiceMapper {
    private final SharedMapper mapper;

    public CreateOrderRequestProto map(CreateOrderRequest request) {
        return CreateOrderRequestProto.newBuilder()
                .setUserId(mapper.mapUserId(request.userId()))
                .setCost(request.cost())
                .setOrderDescription(mapper.map(request.description()))
                .build();
    }

    public Optional<CreateOrderResponse> map(WrappedCreateOrderResponseProto response) {
        if (response.hasError()) {
            return Optional.empty();
        }
        return Optional.of(
            new CreateOrderResponse(mapper.mapOrderId(response.getCreateOrderResponse().getOrderId()))
        );
    }

    public GetOrderMetaRequestProto map(GetOrderMetaRequest request) {
        return GetOrderMetaRequestProto.newBuilder().setOrderId(mapper.mapOrderId(request.orderId())).build();
    }

    public String map(OrderStatusProto orderStatusProto) {
        return orderStatusProto.name();
    }

    public Optional<GetOrderMetaResponse> map(WrappedGetOrderMetaResponseProto response) {
        if (response.hasError()) {
            return Optional.empty();
        }
        return Optional.of(
                new GetOrderMetaResponse(map(response.getGetOrderMetaResponse().getOrderStatus()))
        );
    }

    public GetOrderListRequestProto map(GetOrderListRequest request) {
        return GetOrderListRequestProto.newBuilder().setUserId(mapper.mapUserId(request.userId())).build();
    }

    public Optional<GetOrderListResponse> map(WrappedGetOrderListResponseProto response) {
        if (response.hasError()) {
            return Optional.empty();
        }
        return Optional.of(
            new GetOrderListResponse(response.getGetOrderListResponseProto().getOrderIdsList().stream().map(
                    mapper::mapOrderId).toList()
            )
        );
    }
}
