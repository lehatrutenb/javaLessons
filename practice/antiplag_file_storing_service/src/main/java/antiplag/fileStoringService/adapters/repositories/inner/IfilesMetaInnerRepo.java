package antiplag.fileStoringService.adapters.repositories.inner;

import antiplag.fileStoringService.core.entities.File;
import antiplag.fileStoringService.core.entities.FileMeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IfilesMetaInnerRepo extends JpaRepository<FileMeta, Integer> {
}