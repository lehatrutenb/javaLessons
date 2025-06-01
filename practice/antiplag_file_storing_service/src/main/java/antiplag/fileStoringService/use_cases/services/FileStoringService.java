package antiplag.fileStoringService.use_cases.services;

import antiplag.fileStoringService.core.adapters_interfaces.IfilesDataRepo;
import antiplag.fileStoringService.core.adapters_interfaces.IfilesMetaRepo;
import antiplag.fileStoringService.core.domain_services.ifactories.IfileFactory;
import antiplag.fileStoringService.core.entities.File;
import antiplag.fileStoringService.core.entities.FileMeta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileStoringService {
    private final IfileFactory fileFactory;
    private final IfilesDataRepo filesDataRepo;
    private final IfilesMetaRepo filesMetaRepo;

    public Optional<File> loadFile(Integer id) {
        return fileFactory.createFile(id);
    }

    public FileMeta storeFile(File file) {
        filesDataRepo.save(file);
        filesMetaRepo.save(file.getMeta());
        return file.getMeta();
    }

    public List<FileMeta> getStoringFiles() {
        return filesMetaRepo.getAllFilesMetas();
    }
}
