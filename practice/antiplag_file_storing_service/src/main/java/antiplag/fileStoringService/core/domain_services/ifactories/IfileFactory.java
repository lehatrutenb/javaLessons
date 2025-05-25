package antiplag.fileStoringService.core.domain_services.ifactories;

import antiplag.fileStoringService.core.entities.File;
import antiplag.fileStoringService.core.entities.FileMeta;
import org.springframework.data.util.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface IfileFactory {
    public Pair<File, FileMeta> createFile(String body, String name);
    public Pair<File, FileMeta> createFile(String body, String name, LocalDateTime dateCreated);
    public Optional<File> createFile(Integer id);
    public Optional<FileMeta> createFileMeta(Integer id);
}
