package payment.apigateway.adapters.inPorts.http;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import payment.apigateway.Ishared.IwrappedErrorFactory;
import payment.apigateway.adapters.inPorts.http.exceptions.PaymentException;
import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.ifactories.IgetOrderListFactory;
import payment.apigateway.useCases.dtos.ifactories.IgetOrderMetaFactory;
import payment.apigateway.useCases.dtos.requests.CreateOrderRequest;
import payment.apigateway.useCases.dtos.responses.CreateOrderResponse;
import payment.apigateway.useCases.dtos.responses.GetOrderListResponse;
import payment.apigateway.useCases.dtos.responses.GetOrderMetaResponse;
import payment.apigateway.useCases.iShared.IcreateOrder;
import payment.apigateway.useCases.iShared.IgetOrderList;
import payment.apigateway.useCases.iShared.IgetOrderMeta;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Tag(name="Order service", description = "")
public class OrderController {
    private final IcreateOrder createOrder;
    private final IgetOrderMeta getOrderMeta;
    private final IgetOrderList getOrderList;

    private final IwrappedErrorFactory wrappedErrorFactory;
    private final IgetOrderListFactory getOrderListFactory;
    private final IgetOrderMetaFactory getOrderMetaFactory;

    @PostMapping
    @Operation(summary = "создать заказ")
    public ResponseEntity<CreateOrderResponse> createOrder(
            @Valid @RequestBody CreateOrderRequest request,
            BindingResult bindingResult
            ) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }

        WrappedError error = wrappedErrorFactory.create();
        var response = createOrder.endHandle(request, error);
        if (response.isEmpty() || error.hasError()) {
            log.warn(String.format("failed with code: %d, message: %s", error.getCode(), error.getMessage()));
            throw new PaymentException(error.getCode(), error.getMessage());
            // return ResponseEntity.status(error.getCode()).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @GetMapping("/orders/{userId}")
    @Operation(summary = "получить список заказов пользователя")
    public ResponseEntity<GetOrderListResponse> getOrderList(@PathVariable Integer userId) {
        WrappedError error = wrappedErrorFactory.create();
        var response = getOrderList.endHandle(getOrderListFactory.create(userId), error);
        if (response.isEmpty() || error.hasError()) {
            log.warn(String.format("failed with code: %d, message: %s", error.getCode(), error.getMessage()));
            throw new PaymentException(error.getCode(), error.getMessage());
            // return ResponseEntity.status(error.getCode()).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @GetMapping("/order/{orderId}")
    @Operation(summary = "получить информацию о заказе")
    public ResponseEntity<GetOrderMetaResponse> getOrderMeta(@PathVariable Integer orderId) {
        WrappedError error = wrappedErrorFactory.create();
        var response = getOrderMeta.endHandle(getOrderMetaFactory.create(orderId), error);
        if (response.isEmpty()) {
            log.warn(String.format("failed with code: %d, message: %s", error.getCode(), error.getMessage()));
            throw new PaymentException(error.getCode(), error.getMessage());
            // return ResponseEntity.status(error.getCode()).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }
}
