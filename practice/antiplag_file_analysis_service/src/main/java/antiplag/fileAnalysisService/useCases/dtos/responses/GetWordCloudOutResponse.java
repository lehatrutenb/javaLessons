package antiplag.fileAnalysisService.useCases.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record GetWordCloudOutResponse (
        @Schema(description="Изображение")
        List<Byte> rawImg
) { }
