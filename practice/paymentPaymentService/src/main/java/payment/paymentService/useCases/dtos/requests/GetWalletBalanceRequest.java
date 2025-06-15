package payment.paymentService.useCases.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import payment.paymentService.core.domainServices.holders.IwalletIdHolder;

public record GetWalletBalanceRequest(
        @Schema(description = "wallet id to get balance of", example = "1")
        int walletId
) implements IwalletIdHolder {
}
