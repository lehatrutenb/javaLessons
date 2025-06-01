package antiplag.apigateway.use_cases.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;

public record FileClosenessResponse (
        @Schema(description="ID файла")
        Integer id,

        @Schema(description="Имя файла", example="file.txt")
        String name,

        @Schema(description="Блязость между файлами - чем больше, тем ближе")
        int closenessRank
) { }
