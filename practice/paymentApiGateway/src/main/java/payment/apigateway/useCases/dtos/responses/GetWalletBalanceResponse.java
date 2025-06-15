package payment.apigateway.useCases.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetWalletBalanceResponse(
        @Schema(description = "balance value", example = "1000")
        int amount
) {
}
