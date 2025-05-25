package antiplag.fileAnalysisService.useCases.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

public record FileLoadRequest (
        @Schema(description="ID файла", example="0")
        @Min(value = 0)
        Integer id
) { }