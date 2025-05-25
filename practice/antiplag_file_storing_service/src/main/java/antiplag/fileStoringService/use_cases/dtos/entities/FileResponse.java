package antiplag.fileStoringService.use_cases.dtos.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

public record FileResponse (
        @Schema(description="Мета данные файла")
        FileMetaResponse meta,

        @Schema(description="Тело файла")
        String FileBody
) {}
