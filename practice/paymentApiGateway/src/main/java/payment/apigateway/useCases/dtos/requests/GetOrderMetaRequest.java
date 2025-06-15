package payment.apigateway.useCases.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetOrderMetaRequest(
    @Schema(description = "order id to get info about", example = "1")
    int orderId
) { }
