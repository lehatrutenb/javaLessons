package payment.paymentService.useCases.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetWalletBalanceResponse(
        @Schema(description = "money amount on wallet", example = "1000")
        int amount
) {
}
