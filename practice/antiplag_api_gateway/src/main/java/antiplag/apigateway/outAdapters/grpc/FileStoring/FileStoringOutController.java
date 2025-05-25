package antiplag.apigateway.outAdapters.grpc.FileStoring;

import antiplag.fileStoringService.grpc.StoringServiceGrpc;
import antiplag.apigateway.outAdapters.IfileStoringOutController;
import antiplag.apigateway.use_cases.dtos.requests.FileLoadRequest;
import antiplag.apigateway.use_cases.dtos.requests.FileStoreRequest;
import antiplag.apigateway.use_cases.dtos.responses.FileBodyResponse;
import antiplag.apigateway.use_cases.dtos.responses.FileMetaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FileStoringOutController implements IfileStoringOutController {
    private final StoringServiceGrpc.StoringServiceBlockingStub storingService;
    private final GrpcFileStoringRequestMapping fileStoringRequestMapping;
    private final GrpcFileStoringResponseMapping fileStoringResponseMapping;

    @Override
    public FileMetaResponse storeFile(FileStoreRequest request) {
        return fileStoringResponseMapping.mapFileMetaResponse(
                storingService.storeFile(fileStoringRequestMapping.mapFileStoreRequest(request))
        );
    }

    @Override
    public Optional<FileBodyResponse> loadFile(FileLoadRequest request) {
        return fileStoringResponseMapping.mapFileDataResponse(
                storingService.loadFile(fileStoringRequestMapping.mapFileLoadRequest(request))
        );
    }
}
