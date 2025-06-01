package antiplag.fileStoringService.use_cases.dtos.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

public record FileStoreRequest (
        @Schema(description = "Имя файла", example="file")
        String name,

        @Schema(description = "Содержание файла", example="abacaba")
        @Size(max=10000)
        String body
)
{ }
