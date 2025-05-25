package antiplag.fileAnalysisService.useCases.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

public record GetWordCloudOutRequest(
    @Schema(description = "Текст", example = "abacaba ababa bab")
    String text,
    @Schema(description = "Формат", example = "png")
    @Pattern(regexp = "png|jpg")
    String resultImgFormat
) { }
