package antiplag.apigateway.outAdapters.grpc.FileStoring;

import antiplag.apigateway.use_cases.dtos.responses.FileBodyResponse;
import antiplag.apigateway.use_cases.dtos.responses.FileMetaResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GrpcFileStoringResponseMapping {
    public FileMetaResponse mapFileMetaResponse(antiplag.fileStoringService.grpc.FileMetaResponse response) {
        return new FileMetaResponse(response.getId(), response.getName(), response.getDateCreated());
    }

    public Optional<FileBodyResponse> mapFileDataResponse(antiplag.fileStoringService.grpc.FileDataResponse response) {
        if (response.hasNull()) {
            return Optional.empty();
        }
        return Optional.of(new FileBodyResponse(
                mapFileMetaResponse(response.getFile().getMetaData()), response.getFile().getData()
        ));
    }
}
