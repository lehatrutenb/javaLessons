package antiplag.apigateway.use_cases.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

public record FileLoadRequest(
        @Schema(description = "ID файла", example = "123")
        @Min(value = 0, message = "Минимальный ID")
        int id
) { }
