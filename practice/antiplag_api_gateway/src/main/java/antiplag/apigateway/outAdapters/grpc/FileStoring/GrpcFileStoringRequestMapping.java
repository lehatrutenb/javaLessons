package antiplag.apigateway.outAdapters.grpc.FileStoring;

import antiplag.apigateway.use_cases.dtos.requests.FileLoadRequest;
import antiplag.apigateway.use_cases.dtos.requests.FileStoreRequest;
import org.springframework.stereotype.Component;

@Component
public class GrpcFileStoringRequestMapping {
    public antiplag.fileStoringService.grpc.FileStoreRequest mapFileStoreRequest(FileStoreRequest request) {
        return antiplag.fileStoringService.grpc.FileStoreRequest.newBuilder()
                .setData(request.data()).setName(request.fileName()).build();
    }

    public antiplag.fileStoringService.grpc.FileLoadRequest mapFileLoadRequest(FileLoadRequest request) {
        return antiplag.fileStoringService.grpc.FileLoadRequest.newBuilder()
                .setId(request.id()).build();
    }
}
