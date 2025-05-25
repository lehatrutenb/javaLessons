package antiplag.fileAnalysisService.adapters.outPorts.grpc;

import antiplag.fileAnalysisService.useCases.Ifacade;
import antiplag.fileAnalysisService.useCases.dtos.requests.FileLoadRequest;
import antiplag.fileAnalysisService.useCases.dtos.responses.FileBodyResponse;
import antiplag.fileAnalysisService.useCases.interfaces.IfileLoadOuterOutController;
import com.google.protobuf.Empty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import antiplag.fileStoringService.grpc.StoringServiceGrpc;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class FileLoadOuterOutController implements IfileLoadOuterOutController {
    private final FileLoadOutMapper fileLoadOutMapper;
    private final StoringServiceGrpc.StoringServiceBlockingStub storingService;


    @Override
    public List<Optional<FileBodyResponse>> loadFiles(List<FileLoadRequest> requests) {
        return requests.stream().map(request ->
                fileLoadOutMapper.FileDataResponseToFileBodyResponse(
                        storingService.loadFile(
                                fileLoadOutMapper.getFileLoadRequest(request)
                        )
        )).toList();
    }

    @Override
    public List<FileLoadRequest> getStoringFilesIDs() {
        return storingService.getStoringFilesIDs(Empty.getDefaultInstance())
                .getFilesList().stream().map(fileLoadOutMapper::getFileLoadRequest).toList();
    }
}
