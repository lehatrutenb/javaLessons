package payment.orderService.useCases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import payment.orderService.core.domain_services.ifactories.IorderIdFactory;
import payment.orderService.core.domain_services.ifactories.IorderStatusFactory;
import payment.orderService.core.domain_services.ifactories.IuserIdFactory;
import payment.orderService.shared.WrappedError;
import payment.orderService.useCases.dtos.factories.OrderResponseFactory;
import payment.orderService.useCases.dtos.requests.CreateOrderRequest;
import payment.orderService.useCases.dtos.requests.GetOrderListRequest;
import payment.orderService.useCases.dtos.requests.GetOrderMetaRequest;
import payment.orderService.useCases.dtos.requests.OrderAppliementRequest;
import payment.orderService.useCases.dtos.responses.CreateOrderResponse;
import payment.orderService.useCases.dtos.responses.GetOrderListResponse;
import payment.orderService.useCases.dtos.responses.GetOrderMetaResponse;
import payment.orderService.useCases.ishared.Ifacade;
import payment.orderService.useCases.ishared.IorderService;
import payment.orderService.useCases.services.SubscriberService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Facade implements Ifacade {
    private final IorderService orderService;
    private final IuserIdFactory userIdFactory;
    private final IorderIdFactory orderIdFactory;
    private final OrderResponseFactory responseFactory;
    private final IorderStatusFactory orderStatusFactory;

    @Override
    public Optional<CreateOrderResponse> createOrder(CreateOrderRequest request, WrappedError error) {
        return responseFactory.createCreateResponse(
                orderService.createOrder(userIdFactory.create(request.userId()), request.cost(), request.description()
                                        , error)
        );
    }

    @Override
    public Optional<GetOrderListResponse> getOrderListByUser(GetOrderListRequest request, WrappedError error) {
        return responseFactory.createGetListResponse(
                orderService.getOrderListByUser(userIdFactory.create(request.userId()), error)
        );
    }

    @Override
    public Optional<GetOrderMetaResponse> getOrderMeta(GetOrderMetaRequest request, WrappedError error) {
        return responseFactory.createGetMetaResponse(
                orderService.getOrderMeta(orderIdFactory.create(request.orderId()), error)
        );
    }

    @Override
    public void processOrderAppliementEvent(OrderAppliementRequest request, WrappedError error) {
        orderService.processOrderAppliementEvent(
                orderIdFactory.create(request.orderId()),
                orderStatusFactory.create(request.status()).get(),
                error
                );
    }
}
