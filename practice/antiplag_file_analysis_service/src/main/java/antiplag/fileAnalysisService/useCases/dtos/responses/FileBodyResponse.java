package antiplag.fileAnalysisService.useCases.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;

public record FileBodyResponse(
        @Schema(description="Мета данные файла")
        FileMetaResponse meta,

        @Schema(description="Тело файла")
        String FileBody
) { }
