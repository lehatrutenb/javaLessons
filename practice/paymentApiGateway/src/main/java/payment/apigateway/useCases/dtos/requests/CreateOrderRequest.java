package payment.apigateway.useCases.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateOrderRequest(
        @Schema(description="usedId", example="1")
        int userId,
        @Schema(description="order cost amount", example = "1000")
        int cost,
        @Schema(description="order description", example = "some text")
        String description
) { }
