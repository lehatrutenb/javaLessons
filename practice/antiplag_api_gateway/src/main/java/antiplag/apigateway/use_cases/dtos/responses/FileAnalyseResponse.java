package antiplag.apigateway.use_cases.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record FileAnalyseResponse(
        @Schema(description = "Близжайшие файлы по алнаитическим метрикам")
        List<FileClosenessResponse> nearestFiles
) {
}
