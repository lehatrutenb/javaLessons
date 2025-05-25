package antiplag.fileStoringService.use_cases.dtos.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public record FileLoadRequest (
        @Schema(description="ID файла", example="0")
        @Min(value = 0)
        Integer id
) { }
