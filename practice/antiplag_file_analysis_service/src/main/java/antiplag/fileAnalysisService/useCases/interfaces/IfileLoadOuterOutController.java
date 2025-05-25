package antiplag.fileAnalysisService.useCases.interfaces;

import antiplag.fileAnalysisService.core.entities.File;
import antiplag.fileAnalysisService.useCases.dtos.requests.FileLoadRequest;
import antiplag.fileAnalysisService.useCases.dtos.responses.FileBodyResponse;

import java.util.List;
import java.util.Optional;

public interface IfileLoadOuterOutController {
    public List<Optional<FileBodyResponse>> loadFiles(List<FileLoadRequest> ids);
    public List<FileLoadRequest> getStoringFilesIDs();
}
