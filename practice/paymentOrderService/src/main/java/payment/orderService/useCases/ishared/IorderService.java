package payment.orderService.useCases.ishared;

import payment.orderService.core.entities.OrderId;
import payment.orderService.core.entities.OrderStatus;
import payment.orderService.core.entities.UserId;
import payment.orderService.shared.WrappedError;
import payment.orderService.useCases.dtos.requests.CreateOrderRequest;
import payment.orderService.useCases.dtos.requests.GetOrderListRequest;
import payment.orderService.useCases.dtos.requests.GetOrderMetaRequest;
import payment.orderService.useCases.dtos.responses.CreateOrderResponse;
import payment.orderService.useCases.dtos.responses.GetOrderListResponse;
import payment.orderService.useCases.dtos.responses.GetOrderMetaResponse;

import java.util.List;
import java.util.Optional;

public interface IorderService {
    public Optional<OrderId> createOrder(UserId userId, int cost, String description, WrappedError error);
    public Optional<List<OrderId>> getOrderListByUser(UserId userId, WrappedError error);
    public Optional<OrderStatus> getOrderMeta(OrderId orderId, WrappedError error);
    public void processOrderAppliementEvent(OrderId orderId, OrderStatus newOrderStatus, WrappedError error);
    public void subscribeOnOrderCreation(IorderCreationHandler handler);
}
