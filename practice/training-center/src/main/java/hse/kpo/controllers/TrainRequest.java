package hse.kpo.controllers;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

public record TrainRequest(
        @Schema(description="id посетителя")
        int customerId,
        @Schema(description="тип тренировки", example="HAND_TRAIN")
        @Pattern(regexp="HAND_TRAIN|LEG_TRAIN|IQ_TRAIN")
        String trainType
) {}