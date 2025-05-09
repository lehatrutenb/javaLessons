package antiplag.apigateway.outAdapters.grpc;

import antiplag.apigateway.use_cases.dtos.requests.FileLoadRequest;
import antiplag.apigateway.use_cases.dtos.requests.FileStoreRequest;
import org.springframework.stereotype.Component;

@Component
public class GrpcFileStoringRequestMapping {
    public antiplag.apigateway.grpc.FileStoreRequest mapFileStoreRequest(FileStoreRequest request) {
        return antiplag.apigateway.grpc.FileStoreRequest.newBuilder().setData(request.data()).build();
    }

    public antiplag.apigateway.grpc.FileLoadRequest mapFileLoadRequest(FileLoadRequest request) {
        return antiplag.apigateway.grpc.FileLoadRequest.newBuilder().setId(request.id()).build();
    }
}
