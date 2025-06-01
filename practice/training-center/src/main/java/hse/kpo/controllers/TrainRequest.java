package hse.kpo.controllers;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

public record TrainRequest(
        @Schema(description="id посетителя")
        int customerId,
        @Schema(description="тип тренировки", example="handPower")
        @Pattern(regexp="handPower|legPower|iq")
        String trainType
) {}