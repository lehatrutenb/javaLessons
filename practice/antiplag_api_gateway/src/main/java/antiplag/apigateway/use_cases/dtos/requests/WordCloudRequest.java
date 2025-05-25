package antiplag.apigateway.use_cases.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

public record WordCloudRequest(
        @Schema(description="ID файла", example="0")
        @Min(value = 0)
        Integer id
) {
}
