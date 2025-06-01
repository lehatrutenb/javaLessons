package antiplag.fileAnalysisService.adapters.outPorts.grpc;

import antiplag.fileAnalysisService.adapters.outPorts.GrpcErrorHandler;
import antiplag.fileAnalysisService.useCases.Ifacade;
import antiplag.fileAnalysisService.useCases.dtos.requests.FileLoadRequest;
import antiplag.fileAnalysisService.useCases.dtos.responses.FileBodyResponse;
import antiplag.fileAnalysisService.useCases.interfaces.IfileLoadOuterOutController;
import com.google.protobuf.Empty;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import antiplag.fileStoringService.grpc.StoringServiceGrpc;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FileLoadOuterOutController implements IfileLoadOuterOutController {
    private final FileLoadOutMapper fileLoadOutMapper;
    private final StoringServiceGrpc.StoringServiceBlockingStub storingService;
    private final GrpcErrorHandler errorHandler;

    @Override
    public List<Optional<FileBodyResponse>> loadFiles(List<FileLoadRequest> requests) {
        try {
            return requests.stream().map(request ->
                    fileLoadOutMapper.FileDataResponseToFileBodyResponse(
                            storingService.loadFile(
                                    fileLoadOutMapper.getFileLoadRequest(request)
                            )
                    )).toList();
        } catch (StatusRuntimeException e) {
            errorHandler.handle(e);
            return List.of();
        }
    }

    @Override
    public List<FileLoadRequest> getStoringFilesIDs() {
        try {
            return storingService.getStoringFilesIDs(Empty.getDefaultInstance())
                    .getFilesList().stream().map(fileLoadOutMapper::getFileLoadRequest).toList();
        } catch (StatusRuntimeException e) {
            errorHandler.handle(e);
            return List.of();
        }
    }
}
