package antiplag.fileStoringService.adapters.in_ports.grpc;

import antiplag.fileStoringService.grpc.*;
import antiplag.fileStoringService.use_cases.Ifacade;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@RequiredArgsConstructor
@GrpcService
public class FileStoringGrpcController extends StoringServiceGrpc.StoringServiceImplBase {
    private final Ifacade facade;
    private final FileMappingGrpc fileMappingGrpc;

    @Override
    public void storeFile(FileStoreRequest fileStoreRequest, StreamObserver<FileMetaResponse> responseStreamObserver) {
        var fileMetaResponse = facade.storeFile(
             fileMappingGrpc.convertToFileStoreRequest(fileStoreRequest)
        );
        responseStreamObserver.onNext(
                fileMappingGrpc.getFileMetaResponse(fileMetaResponse)
        );
        responseStreamObserver.onCompleted();
    }

    @Override
    public void loadFile(FileLoadRequest fileLoadRequest, StreamObserver<FileDataResponse> responseStreamObserver) {
        var fileResponse = facade.loadFile(
                fileMappingGrpc.convertToFileLoadRequest(fileLoadRequest)
        );
        responseStreamObserver.onNext(
                fileMappingGrpc.getFileDataResponse(fileResponse)
        );
        responseStreamObserver.onCompleted();
    }

    @Override
    public void getStoringFilesIDs(Empty request, StreamObserver<StoringFilesResponse> responseStreamObserver) {
        responseStreamObserver.onNext(
                fileMappingGrpc.getStoringFilesResponse(facade.getStoringFiles())
        );
        responseStreamObserver.onCompleted();
    }
}
