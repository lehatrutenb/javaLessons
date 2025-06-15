package payment.orderService.useCases.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

public record OrderAppliementRequest(
        @Schema(description = "order id to get info about", example = "1")
        int orderId,
        @Schema(description = "order set status", example = "FINISHED")
        @Pattern(regexp = "CANCELLED|FINISHED")
        String status
) {
}
