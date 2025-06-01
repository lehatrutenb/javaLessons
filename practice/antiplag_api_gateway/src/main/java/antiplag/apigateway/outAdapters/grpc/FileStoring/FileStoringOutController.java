package antiplag.apigateway.outAdapters.grpc.FileStoring;

import antiplag.apigateway.outAdapters.grpc.GrpcErrorHandler;
import antiplag.fileStoringService.grpc.StoringServiceGrpc;
import antiplag.apigateway.outAdapters.IfileStoringOutController;
import antiplag.apigateway.use_cases.dtos.requests.FileLoadRequest;
import antiplag.apigateway.use_cases.dtos.requests.FileStoreRequest;
import antiplag.apigateway.use_cases.dtos.responses.FileBodyResponse;
import antiplag.apigateway.use_cases.dtos.responses.FileMetaResponse;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileStoringOutController implements IfileStoringOutController {
    private final StoringServiceGrpc.StoringServiceBlockingStub storingService;
    private final GrpcFileStoringRequestMapping fileStoringRequestMapping;
    private final GrpcFileStoringResponseMapping fileStoringResponseMapping;
    private final GrpcErrorHandler errorHandler;

    @Override
    public FileMetaResponse storeFile(FileStoreRequest request) {
        try {
            return fileStoringResponseMapping.mapFileMetaResponse(
                    storingService.storeFile(fileStoringRequestMapping.mapFileStoreRequest(request))
            );
        } catch (StatusRuntimeException e) {
            errorHandler.handle(e);
            return null;
        }
    }

    @Override
    public Optional<FileBodyResponse> loadFile(FileLoadRequest request) {
        return fileStoringResponseMapping.mapFileDataResponse(
                storingService.loadFile(fileStoringRequestMapping.mapFileLoadRequest(request))
        );
    }
}
