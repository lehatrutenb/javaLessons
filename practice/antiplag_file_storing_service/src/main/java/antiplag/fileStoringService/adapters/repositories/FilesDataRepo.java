package antiplag.fileStoringService.adapters.repositories;

import antiplag.fileStoringService.adapters.repositories.inner.IfilesDataInnerRepo;
import antiplag.fileStoringService.core.adapters_interfaces.IfilesDataRepo;
import antiplag.fileStoringService.core.entities.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FilesDataRepo implements IfilesDataRepo {
    private final IfilesDataInnerRepo filesDataInnerRepo;

    @Override
    public Optional<File> findById(Integer id) {
        return filesDataInnerRepo.findById(id);
    }

    @Override
    public void save(File file) {
        filesDataInnerRepo.save(file);
    }
}
