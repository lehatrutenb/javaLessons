package antiplag.fileAnalysisService.useCases.dtos.innerComponents;

import antiplag.fileAnalysisService.core.entities.File;
import antiplag.fileAnalysisService.useCases.dtos.mappers.FileLoadResponseToFileMapper;
import antiplag.fileAnalysisService.useCases.dtos.mappers.IdsToFileLoadRequestMapper;
import antiplag.fileAnalysisService.useCases.dtos.requests.FileLoadRequest;
import antiplag.fileAnalysisService.useCases.interfaces.IfileLoadOuterOutController;
import antiplag.fileAnalysisService.useCases.interfaces.IfilesLoadInnerOutController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FileLoader implements IfilesLoadInnerOutController {
    private final IfileLoadOuterOutController fileLoadOuterOutController;
    private final IdsToFileLoadRequestMapper idsToFileLoadRequestMapper;
    private final FileLoadResponseToFileMapper fileLoadResponseToFileMapper;

    @Override
    public List<Optional<File>> loadFiles(List<Integer> ids) {
        return fileLoadResponseToFileMapper.getFiles(
                fileLoadOuterOutController.loadFiles(
                        idsToFileLoadRequestMapper.getFileLoadRequests(ids)
                )
        );
    }

    @Override
    public List<Integer> getStoringFilesIDs() {
        return fileLoadOuterOutController.getStoringFilesIDs().stream().map(
                FileLoadRequest::id
        ).toList();
    }


}
