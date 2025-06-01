package antiplag.fileStoringService.use_cases;

import antiplag.fileStoringService.use_cases.dtos.entities.FileLoadRequest;
import antiplag.fileStoringService.use_cases.dtos.entities.FileMetaResponse;
import antiplag.fileStoringService.use_cases.dtos.entities.FileResponse;
import antiplag.fileStoringService.use_cases.dtos.entities.FileStoreRequest;

import java.util.List;
import java.util.Optional;

public interface Ifacade {
    public Optional<FileResponse> loadFile(FileLoadRequest fileLoadRequest);
    public FileMetaResponse storeFile(FileStoreRequest fileStoreRequest);
    public List<FileLoadRequest> getStoringFiles();
}
