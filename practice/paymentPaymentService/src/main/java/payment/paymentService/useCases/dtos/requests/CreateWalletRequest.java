package payment.paymentService.useCases.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import payment.paymentService.core.domainServices.holders.IuserIdHolder;

public record CreateWalletRequest(
        @Schema(description = "user id", example = "1")
        @Min(value = 1)
        int userId
) implements IuserIdHolder {
}
