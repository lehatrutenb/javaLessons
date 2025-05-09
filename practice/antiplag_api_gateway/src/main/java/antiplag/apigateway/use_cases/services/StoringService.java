package antiplag.apigateway.use_cases.services;

import antiplag.apigateway.outAdapters.IfileStoringOutController;
import antiplag.apigateway.outAdapters.grpc.FileStoringOutController;
import antiplag.apigateway.use_cases.dtos.requests.FileLoadRequest;
import antiplag.apigateway.use_cases.dtos.requests.FileStoreRequest;
import antiplag.apigateway.use_cases.dtos.responses.FileBodyResponse;
import antiplag.apigateway.use_cases.dtos.responses.FileMetaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoringService {
    private final IfileStoringOutController fileStoringOutController;

    public FileMetaResponse storeFile(FileStoreRequest request) {
        return fileStoringOutController.storeFile(request);
    }
    public FileBodyResponse loadFile(FileLoadRequest request) {
        return fileStoringOutController.loadFile(request);
    }
}
