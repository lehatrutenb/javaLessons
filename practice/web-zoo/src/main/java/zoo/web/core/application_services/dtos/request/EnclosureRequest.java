package zoo.web.core.application_services.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public record EnclosureRequest(
        @Schema(description="Ядность животного", example="HERBIVORE")
        @Pattern(regexp="HERBIVORE|PREDATOR|BIRD|WATERLIVING")
        String animalEatType,

        @Schema(description="Размер вольера", example="10")
        @Min(value = 1, message = "Минимальный размер вольера")
        @Max(value = 100, message = "Максимальный размер вольера")
        int size,

        @Schema(description="Макс кол-во животных", example="15")
        @Min(value = 1, message = "Минимальное макс кол-во животных")
        @Max(value = 30, message = "Максимальное макс кол-во животных")
        int maxAnimalAmount
) {}
