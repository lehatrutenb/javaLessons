package zoo.web.core.application_services.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

public record FeedingTimeRequest(
        @Schema(description="id животного")
        String animalId,

        @Schema(description="Первая дата и время кормления", example="01.04.2005 10:00")
        @Pattern(regexp="^(0[1-9]|[12]\\d|3[01])\\.(0[1-9]|1[1,2])\\.\\d{4} ([0-1]\\d|2[0-4]):([0-5]\\d|60)$")
        String firstFeedTime,

        @Schema(description="Период между кормлениями (часы)", example="6")
        int periodH,

        @Schema(description="еда для кормления", example="bone")
        String food
) {}
