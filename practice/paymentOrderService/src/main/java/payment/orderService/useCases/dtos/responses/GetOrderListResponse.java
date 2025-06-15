package payment.orderService.useCases.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record GetOrderListResponse(
        @Schema(description = "list of orders ids")
        List<Integer> ids
) {
}
