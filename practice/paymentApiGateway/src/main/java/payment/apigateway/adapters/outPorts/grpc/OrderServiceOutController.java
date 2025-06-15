package payment.apigateway.adapters.outPorts.grpc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import payment.apigateway.adapters.outPorts.grpc.mappers.OrderServiceMapper;
import payment.apigateway.adapters.outPorts.grpc.mappers.WrappedErrorMapper;
import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.requests.CreateOrderRequest;
import payment.apigateway.useCases.dtos.requests.GetOrderListRequest;
import payment.apigateway.useCases.dtos.requests.GetOrderMetaRequest;
import payment.apigateway.useCases.dtos.responses.CreateOrderResponse;
import payment.apigateway.useCases.dtos.responses.GetOrderListResponse;
import payment.apigateway.useCases.dtos.responses.GetOrderMetaResponse;
import payment.apigateway.useCases.iShared.outPorts.IorderServiceOutController;
import payment.apigateway.grpc.OrderServiceGrpc;
import payment.apigateway.grpc.WrappedCreateOrderResponseProto;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderServiceOutController implements IorderServiceOutController {
    private final OrderServiceGrpc.OrderServiceBlockingStub orderServiceBlockingStub;
    private final OrderServiceMapper orderServiceMapper;
    private final WrappedErrorMapper wrappedErrorMapper;

    @Override
    public Optional<CreateOrderResponse> createOrder(CreateOrderRequest request, WrappedError err) {
        var response = orderServiceBlockingStub.createOrder(
                orderServiceMapper.map(request)
        );

        if (response.hasError()) {
            wrappedErrorMapper.map(response.getError(), err);
        }
        return orderServiceMapper.map(response);
    }

    @Override
    public Optional<GetOrderListResponse> getOrderList(GetOrderListRequest request, WrappedError err) {
        var response = orderServiceBlockingStub.getOrderList(
                orderServiceMapper.map(request)
        );
        if (response.hasError()) {
            wrappedErrorMapper.map(response.getError(), err);
        }
        return orderServiceMapper.map(response);
    }

    @Override
    public Optional<GetOrderMetaResponse> getOrderMeta(GetOrderMetaRequest request, WrappedError err) {
        var response = orderServiceBlockingStub.getOrderMeta(
                orderServiceMapper.map(request)
        );
        if (response.hasError()) {
            wrappedErrorMapper.map(response.getError(), err);
        }
        return orderServiceMapper.map(response);
    }
}
