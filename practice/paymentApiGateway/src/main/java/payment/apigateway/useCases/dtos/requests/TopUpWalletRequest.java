package payment.apigateway.useCases.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

public record TopUpWalletRequest(
        @Schema(description = "walled unique id", example = "1")
        @Min(value=1)
        int walletId,
        @Schema(description = "money amount", example = "1000")
        @Min(value=0)
        int amount
) {
}
