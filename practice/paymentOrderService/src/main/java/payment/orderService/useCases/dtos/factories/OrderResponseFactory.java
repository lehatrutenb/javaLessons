package payment.orderService.useCases.dtos.factories;

import org.springframework.stereotype.Component;
import payment.orderService.core.entities.OrderId;
import payment.orderService.core.entities.OrderStatus;
import payment.orderService.useCases.dtos.responses.CreateOrderResponse;
import payment.orderService.useCases.dtos.responses.GetOrderListResponse;
import payment.orderService.useCases.dtos.responses.GetOrderMetaResponse;

import java.util.List;
import java.util.Optional;

@Component
public class OrderResponseFactory {
    public Optional<CreateOrderResponse> createCreateResponse(Optional<OrderId> response) {
        return response.map(orderId -> new CreateOrderResponse(orderId.getId()));
    }

    public Optional<GetOrderMetaResponse> createGetMetaResponse(Optional<OrderStatus> response) {
        return response.map(status -> new GetOrderMetaResponse(status.name()));
    }

    public Optional<GetOrderListResponse> createGetListResponse(Optional<List<OrderId>> response) {
        return response.map(arr -> new GetOrderListResponse(arr.stream().map(OrderId::getId).toList()));
    }
}
