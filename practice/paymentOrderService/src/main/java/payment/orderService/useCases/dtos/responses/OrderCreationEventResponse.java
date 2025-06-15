package payment.orderService.useCases.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

public record OrderCreationEventResponse(
        @Schema(description = "created order id", example = "1")
        int orderId,
         @Schema(description = "order current status", example = "NEW")
         @Pattern(regexp="NEW|FINISHED|CANCELLED")
         String orderStatus
) {
}
