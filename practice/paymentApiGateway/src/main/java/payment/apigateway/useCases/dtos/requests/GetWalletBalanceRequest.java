package payment.apigateway.useCases.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

public record GetWalletBalanceRequest (
        @Schema(description = "walled unique id", example = "1")
        @Min(value=1)
        int walletId
) {
}
