package payment.paymentService.useCases.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import payment.paymentService.core.domainServices.holders.IorderIdHolder;
import payment.paymentService.core.domainServices.holders.IorderStatusHolder;
import payment.paymentService.core.domainServices.holders.IuserIdHolder;

public record OrderAppliementRequest(
        @Schema(description="orderId", example="1")
        int orderId,
        @Schema(description="usedId", example="1")
        int userId,
        @Schema(description="order cost amount", example = "1000")
        int cost,
        @Schema(description="order status", example = "NEW")
        @Pattern(regexp="NEW")
        String status
) implements IorderIdHolder, IuserIdHolder, IorderStatusHolder {
}
