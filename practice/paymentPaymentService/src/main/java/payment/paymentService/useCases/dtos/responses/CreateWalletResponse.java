package payment.paymentService.useCases.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

public record CreateWalletResponse(
        @Schema(description = "wallet id of newly created wallet", example = "1")
        @Min(value=1)
        int walletId
) {
}
