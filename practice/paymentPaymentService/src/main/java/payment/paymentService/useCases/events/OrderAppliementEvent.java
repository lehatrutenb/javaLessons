package payment.paymentService.useCases.events;

import io.swagger.v3.oas.annotations.media.Schema;
import payment.paymentService.core.entities.OrderId;
import payment.paymentService.core.entities.OrderStatus;
import payment.paymentService.core.entities.UserId;

public record OrderAppliementEvent (
        @Schema(description="orderId")
        OrderId orderId,
        @Schema(description="usedId")
        UserId userId,
        @Schema(description="order cost amount", example = "1000")
        int cost,
        @Schema(description="order status after appliement")
        OrderStatus status
) {
}
