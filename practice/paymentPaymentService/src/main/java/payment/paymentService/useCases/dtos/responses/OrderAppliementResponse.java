package payment.paymentService.useCases.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import payment.paymentService.core.domainServices.holders.IorderIdHolder;
import payment.paymentService.core.domainServices.holders.IorderStatusHolder;
import payment.paymentService.core.domainServices.holders.IuserIdHolder;

public record OrderAppliementResponse(
        @Schema(description="orderId", example="1")
        int orderId,
        @Schema(description="order status", example = "FINISHED")
        @Pattern(regexp="FINISHED|CANCELLED")
        String status
) implements IorderIdHolder, IorderStatusHolder {
}
