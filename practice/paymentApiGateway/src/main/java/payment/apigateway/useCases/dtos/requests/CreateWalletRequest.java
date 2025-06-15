package payment.apigateway.useCases.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateWalletRequest(
        @Schema(description = "user id of wallet master", example = "1")
        int userId
) {
}
