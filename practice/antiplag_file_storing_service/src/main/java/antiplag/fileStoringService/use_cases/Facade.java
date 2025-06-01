package antiplag.fileStoringService.use_cases;

import antiplag.fileStoringService.core.adapters_interfaces.IfilesDataRepo;
import antiplag.fileStoringService.core.adapters_interfaces.IfilesMetaRepo;
import antiplag.fileStoringService.core.domain_services.ifactories.IfileFactory;
import antiplag.fileStoringService.core.entities.File;
import antiplag.fileStoringService.core.entities.FileMeta;
import antiplag.fileStoringService.use_cases.dtos.entities.FileLoadRequest;
import antiplag.fileStoringService.use_cases.dtos.entities.FileMetaResponse;
import antiplag.fileStoringService.use_cases.dtos.entities.FileResponse;
import antiplag.fileStoringService.use_cases.dtos.entities.FileStoreRequest;
import antiplag.fileStoringService.use_cases.mappings.FileMapping;
import antiplag.fileStoringService.use_cases.services.FileStoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Facade implements Ifacade {
    private final FileStoringService fileStoringService;
    private final FileMapping fileMapping;

    @Override
    public Optional<FileResponse> loadFile(FileLoadRequest fileLoadRequest) {
        var result = fileStoringService.loadFile(fileLoadRequest.id());
        return result.map(fileMapping::FileToFileResponse);
    }

    @Override
    public FileMetaResponse storeFile(FileStoreRequest fileStoreRequest) {
        return fileMapping.FileMetaToFileMetaResponse(
                fileStoringService.storeFile(fileMapping.FileStoreRequestToFile(fileStoreRequest))
        );
    }

    @Override
    public List<FileLoadRequest> getStoringFiles() {
        return fileMapping.FileMetasToLoadRequests(
                fileStoringService.getStoringFiles()
        );
    }
}
