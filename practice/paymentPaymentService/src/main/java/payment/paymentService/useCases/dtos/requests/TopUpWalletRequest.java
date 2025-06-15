package payment.paymentService.useCases.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import payment.paymentService.core.domainServices.holders.IwalletIdHolder;

public record TopUpWalletRequest(
        @Schema(description = "walled it to top uo to", example = "1")
        @Min(value = 1)
        int walletId,
        @Schema(description = "top up amount", example = "1000")
        @Min(value=1)
        int amount
) implements IwalletIdHolder {
}
