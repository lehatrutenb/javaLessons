package antiplag.fileStoringService.adapters.repositories;

import antiplag.fileStoringService.adapters.repositories.inner.IfilesMetaInnerRepo;
import antiplag.fileStoringService.core.adapters_interfaces.IfilesMetaRepo;
import antiplag.fileStoringService.core.entities.FileMeta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FilesMetaRepo implements IfilesMetaRepo {
    private final IfilesMetaInnerRepo filesMetaInnerRepo;

    @Override
    public Optional<FileMeta> findById(Integer id) {
        return filesMetaInnerRepo.findById(id);
    }

    @Override
    public FileMeta save(FileMeta fileMeta) {
        return filesMetaInnerRepo.save(fileMeta);
    }

    @Override
    public List<FileMeta> getAllFilesMetas() {
        return filesMetaInnerRepo.findAll();
    }
}
