package antiplag.fileAnalysisService.useCases.dtos.mappers;

import antiplag.fileAnalysisService.core.domain_services.factories.FileFactory;
import antiplag.fileAnalysisService.core.entities.File;
import antiplag.fileAnalysisService.useCases.dtos.responses.FileBodyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FileLoadResponseToFileMapper {
    private final FileFactory fileFactory;
    public Optional<File> getFile(Optional<FileBodyResponse> response) {
        if (response.isEmpty()) {
            return Optional.empty();
        }
        var r = response.get();
        return Optional.of(
                fileFactory.createFile(r.meta().id(), r.meta().fileName(), r.FileBody())
        );
    }

    public List<Optional<File>> getFiles(List<Optional<FileBodyResponse>> responses) {
        return responses.stream().map(this::getFile).toList();
    }
}
