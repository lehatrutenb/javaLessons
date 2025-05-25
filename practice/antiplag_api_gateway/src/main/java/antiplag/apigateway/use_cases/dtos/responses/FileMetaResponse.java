package antiplag.apigateway.use_cases.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

public record FileMetaResponse(
        @Schema(description="ID файла")
        Integer id,

        @Schema(description="Имя файла")
        String fileName,

        @Schema(description="Дата создания файла", example="01.04.2005 10:00")
        @Pattern(regexp="^(0[1-9]|[12]\\d|3[01])\\.(0[1-9]|1[1,2])\\.\\d{4} ([0-1]\\d|2[0-4]):([0-5]\\d|60)$")
        String dateCreated
) { }
