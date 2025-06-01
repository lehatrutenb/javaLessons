package antiplag.fileStoringService.core.adapters_interfaces;

import antiplag.fileStoringService.core.entities.File;
import antiplag.fileStoringService.core.entities.FileMeta;

import java.util.List;
import java.util.Optional;

public interface IfilesMetaRepo {
    Optional<FileMeta> findById(Integer id);
    FileMeta save(FileMeta fileMeta); // void to add contract that save not change entity
    List<FileMeta> getAllFilesMetas();
}
