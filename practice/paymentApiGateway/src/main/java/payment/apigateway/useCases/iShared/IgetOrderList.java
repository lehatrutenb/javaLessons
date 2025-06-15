package payment.apigateway.useCases.iShared;

import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.requests.GetOrderListRequest;
import payment.apigateway.useCases.dtos.responses.GetOrderListResponse;

import java.util.Optional;

public interface IgetOrderList {
    public Optional<GetOrderListResponse> endHandle(GetOrderListRequest request, WrappedError error);
}
