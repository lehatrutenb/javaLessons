package antiplag.fileAnalysisService.useCases.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record GetWordCloudInResponse(
        @Schema(description="Изображение")
        List<Byte> rawImg,

        @Schema(description="Формат изображения", example="PNG")
        @Pattern(regexp="PNG|JPG")
        String imgFormat
) { }
