package antiplag.fileStoringService.adapters.repositories.inner;

import antiplag.fileStoringService.core.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IfilesDataInnerRepo extends JpaRepository<File, Integer> {
}