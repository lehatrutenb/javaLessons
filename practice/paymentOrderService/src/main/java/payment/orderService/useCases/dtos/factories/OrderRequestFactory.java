package payment.orderService.useCases.dtos.factories;

import org.springframework.stereotype.Component;
import payment.orderService.core.entities.Order;
import payment.orderService.useCases.dtos.ifactories.IorderRequestFactory;
import payment.orderService.useCases.dtos.requests.CreateOrderRequest;
import payment.orderService.useCases.dtos.requests.GetOrderListRequest;
import payment.orderService.useCases.dtos.requests.GetOrderMetaRequest;

import java.util.Optional;

@Component
public class OrderRequestFactory implements IorderRequestFactory {
    @Override
    public CreateOrderRequest create(int userId, int cost, String orderDescription) {
        return new CreateOrderRequest(userId, cost, orderDescription);
    }

    @Override
    public CreateOrderRequest create(Order order) {
        return new CreateOrderRequest(
                order.getUserId().getId(), order.getCost(),
                order.getDescription().getText());
    }

    @Override
    public GetOrderMetaRequest createGetOrderMeta(int orderId) {
        return new GetOrderMetaRequest(orderId);
    }

    @Override
    public GetOrderListRequest createGetOrderList(int userId) {
        return new GetOrderListRequest(userId);
    }
}
