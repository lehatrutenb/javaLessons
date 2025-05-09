package antiplag.apigateway.use_cases.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;

public record FileStoreRequest(
        @Schema(description = "тело файла", example = "Some file data")
        String data
) { }

