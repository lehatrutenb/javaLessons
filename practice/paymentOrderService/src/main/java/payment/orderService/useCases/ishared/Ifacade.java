package payment.orderService.useCases.ishared;

import payment.orderService.shared.WrappedError;
import payment.orderService.useCases.dtos.requests.CreateOrderRequest;
import payment.orderService.useCases.dtos.requests.GetOrderListRequest;
import payment.orderService.useCases.dtos.requests.GetOrderMetaRequest;
import payment.orderService.useCases.dtos.requests.OrderAppliementRequest;
import payment.orderService.useCases.dtos.responses.CreateOrderResponse;
import payment.orderService.useCases.dtos.responses.GetOrderListResponse;
import payment.orderService.useCases.dtos.responses.GetOrderMetaResponse;

import java.util.List;
import java.util.Optional;

public interface Ifacade {
    public Optional<CreateOrderResponse> createOrder(CreateOrderRequest request, WrappedError error);
    public Optional<GetOrderListResponse> getOrderListByUser(GetOrderListRequest request, WrappedError error);
    public Optional<GetOrderMetaResponse> getOrderMeta(GetOrderMetaRequest request, WrappedError error);
    public void processOrderAppliementEvent(OrderAppliementRequest resquest, WrappedError error);
}
