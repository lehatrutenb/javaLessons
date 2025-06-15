package payment.orderService.useCases.events;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import payment.orderService.core.entities.OrderDescription;
import payment.orderService.core.entities.OrderId;
import payment.orderService.core.entities.UserId;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_ABSENT) // not to include in json null fields
public record OrderCreationEvent (
        @Schema(description="orderId")
        OrderId orderId,
        @Schema(description="usedId")
        UserId userId,
        @Schema(description="order cost amount", example = "1000")
        int cost,
        @Schema(description="order description")
        OrderDescription description
) {
}
