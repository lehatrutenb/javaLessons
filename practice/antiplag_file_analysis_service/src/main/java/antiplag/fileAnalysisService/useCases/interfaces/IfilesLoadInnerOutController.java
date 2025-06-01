package antiplag.fileAnalysisService.useCases.interfaces;

import antiplag.fileAnalysisService.core.entities.File;

import java.util.List;
import java.util.Optional;

public interface IfilesLoadInnerOutController {
    public List<Optional<File>> loadFiles(List<Integer> ids);
    public List<Integer> getStoringFilesIDs();
}
