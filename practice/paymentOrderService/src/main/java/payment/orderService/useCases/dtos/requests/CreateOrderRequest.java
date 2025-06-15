package payment.orderService.useCases.dtos.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Optional;

public record CreateOrderRequest (
        @Schema(description="usedId", example="1")
        int userId,
        @Schema(description="order cost amount", example = "1000")
        int cost,
        @Schema(description="order description", example = "some text")
        String description
) {
}
