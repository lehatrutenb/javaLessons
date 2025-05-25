package antiplag.fileStoringService.use_cases.mappings;

import antiplag.fileStoringService.core.domain_services.factories.FileFactory;
import antiplag.fileStoringService.core.entities.File;
import antiplag.fileStoringService.core.entities.FileMeta;
import antiplag.fileStoringService.use_cases.dtos.entities.FileLoadRequest;
import antiplag.fileStoringService.use_cases.dtos.entities.FileMetaResponse;
import antiplag.fileStoringService.use_cases.dtos.entities.FileResponse;
import antiplag.fileStoringService.use_cases.dtos.entities.FileStoreRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FileMapping {
    private final FileFactory fileFactory;

    public FileResponse FileToFileResponse(File file) {
        return new FileResponse(FileMetaToFileMetaResponse(file.getMeta()), file.getBody());
    }

    public FileMetaResponse FileMetaToFileMetaResponse(FileMeta fileMeta) {
        return new FileMetaResponse(fileMeta.getId(), fileMeta.getName(), fileMeta.getDateCreated().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
    }

    public File FileStoreRequestToFile(FileStoreRequest fileStoreRequest) {
        return fileFactory.createFile(fileStoreRequest.body(), fileStoreRequest.name()).getFirst();
    }

    private FileLoadRequest FileToLoadRequest(FileMeta file) {
        return new FileLoadRequest(file.getId());
    }

    public List<FileLoadRequest> FileMetasToLoadRequests(List<FileMeta> files) {
        return files.stream().map(this::FileToLoadRequest).toList();
    }
}
