package antiplag.apigateway.outAdapters.grpc;

import antiplag.apigateway.grpc.StoringServiceGrpc;
import antiplag.apigateway.outAdapters.IfileStoringOutController;
import antiplag.apigateway.use_cases.dtos.requests.FileLoadRequest;
import antiplag.apigateway.use_cases.dtos.requests.FileStoreRequest;
import antiplag.apigateway.use_cases.dtos.responses.FileBodyResponse;
import antiplag.apigateway.use_cases.dtos.responses.FileMetaResponse;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

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
    public FileBodyResponse loadFile(FileLoadRequest request) {
        return fileStoringResponseMapping.mapFileBodyResponse(
                storingService.loadFile(fileStoringRequestMapping.mapFileLoadRequest(request))
        );
    }
    // (FileStoreRequest request, StreamObserver<antiplag.apigateway.grpc.FileMetaResponse> response) {
        //request.
        /*String report = hse.generateReport();
        ReportResponse response = ReportResponse.newBuilder()
                .setTitle("Sales Report")
                .setContent(report)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }*/
}
