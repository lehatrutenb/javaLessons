package antiplag.fileStoringService.core.adapters_interfaces;

import antiplag.fileStoringService.core.entities.File;

import java.util.Optional;

public interface IfilesDataRepo {
    Optional<File> findById(Integer id);
    void save(File file); // void to add contract that save not change entity
}
