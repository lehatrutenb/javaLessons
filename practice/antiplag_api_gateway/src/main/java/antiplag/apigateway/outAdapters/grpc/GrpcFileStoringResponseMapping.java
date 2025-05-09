package antiplag.apigateway.outAdapters.grpc;

import antiplag.apigateway.use_cases.dtos.requests.FileLoadRequest;
import antiplag.apigateway.use_cases.dtos.requests.FileStoreRequest;
import antiplag.apigateway.use_cases.dtos.responses.FileBodyResponse;
import antiplag.apigateway.use_cases.dtos.responses.FileMetaResponse;
import org.springframework.stereotype.Component;

@Component
public class GrpcFileStoringResponseMapping {
    public FileMetaResponse mapFileMetaResponse(antiplag.apigateway.grpc.FileMetaResponse response) {
        return new FileMetaResponse(response.getId(), response.getDateCreated());
    }

    public FileBodyResponse mapFileBodyResponse(antiplag.apigateway.grpc.FileBodyResponse response) {
        return new FileBodyResponse(response.getData());
    }
}
