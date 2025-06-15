package payment.apigateway.useCases.iShared.outPorts;

import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.requests.GetOrderListRequest;
import payment.apigateway.useCases.dtos.responses.GetOrderListResponse;

import java.util.Optional;

public interface IgetOrderListOutPort {
    public Optional<GetOrderListResponse> getOrderList(GetOrderListRequest request, WrappedError error);
}