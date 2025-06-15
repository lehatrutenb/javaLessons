package payment.orderService.useCases.dtos.ifactories;

import payment.orderService.core.entities.Order;
import payment.orderService.useCases.dtos.requests.CreateOrderRequest;
import payment.orderService.useCases.dtos.requests.GetOrderListRequest;
import payment.orderService.useCases.dtos.requests.GetOrderMetaRequest;

public interface IorderRequestFactory {
    public CreateOrderRequest create(int userId, int cost, String orderDescription);

    public CreateOrderRequest create(Order order);

    public GetOrderMetaRequest createGetOrderMeta(int orderId);

    public GetOrderListRequest createGetOrderList(int userId);
}
