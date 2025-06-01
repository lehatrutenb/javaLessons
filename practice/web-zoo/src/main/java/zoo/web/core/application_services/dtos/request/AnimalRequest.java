package zoo.web.core.application_services.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import jakarta.validation.constraints.Pattern;
import zoo.web.core.entities.animals.AnimalEatType;
import zoo.web.core.entities.animals.AnimalMale;

@Builder
public record AnimalRequest(
        @Schema(description="Ядность животного", example="HERBIVORE")
        @Pattern(regexp="HERBIVORE|PREDATOR|BIRD|WATERLIVING")
        String animalEatType,

        @Schema(description="Вид животного", example="dog")
        String animalType,

        @Schema(description="Пол животного", example="MALE")
        @Pattern(regexp="MALE|FEMALE|UNIFY")
        String animalMale,

        @Schema(description="Любимая еда", example="bone")
        String favoriteFood,

        @Schema(description="День рождения", example="01.04.2005")
        @Pattern(regexp="^(0[1-9]|[12]\\d|3[01])\\.(0[1-9]|1[1,2])\\.\\d{4}$")
        String birthday,

        @Schema(description="Здоровое ли животное", example="true")
        @Pattern(regexp="true|false")
        String isHealthy
) {}
