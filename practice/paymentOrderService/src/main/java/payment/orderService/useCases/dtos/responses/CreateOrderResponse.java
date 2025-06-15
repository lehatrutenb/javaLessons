package payment.orderService.useCases.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateOrderResponse(
        @Schema(description = "created order id", example = "1")
        int orderId
) {
}
