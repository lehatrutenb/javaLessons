package payment.orderService.useCases.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetOrderListRequest (
        @Schema(description = "user id to get all orders from him", example = "1")
        int userId
){
}
