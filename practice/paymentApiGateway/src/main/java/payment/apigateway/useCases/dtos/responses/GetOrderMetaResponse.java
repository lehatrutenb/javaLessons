package payment.apigateway.useCases.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

public record GetOrderMetaResponse(
    @Schema(description = "order current status", example = "NEW")
    @Pattern(regexp="NEW|FINISHED|CANCELLED")
    String orderStatus
) { }
