package antiplag.fileStoringService.core.domain_services.factories;

import antiplag.fileStoringService.core.adapters_interfaces.IfilesDataRepo;
import antiplag.fileStoringService.core.adapters_interfaces.IfilesMetaRepo;
import antiplag.fileStoringService.core.domain_services.ifactories.IfileFactory;
import antiplag.fileStoringService.core.entities.File;
import antiplag.fileStoringService.core.entities.FileMeta;
import antiplag.fileStoringService.ishared.ItimeManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class FileFactory implements IfileFactory {
    private final ItimeManager timeManager;
    private final IfilesMetaRepo filesMetaRepo;
    private final IfilesDataRepo filesDataRepo;

    @Override
    public Pair<File, FileMeta> createFile(String body, String name) {
        return createFile(body, name, timeManager.getCurLocalDate());
    }

    @Override
    public Pair<File, FileMeta> createFile(String body, String name, LocalDateTime dateCreated) {
        var meta = new FileMeta(name, dateCreated);
        var file = new File(body, meta);
        filesDataRepo.save(file);

        return Pair.of(new File(body, meta), meta);
    }

    @Override
    public Optional<File> createFile(Integer id) {
        return filesDataRepo.findById(id);
    }

    @Override
    public Optional<FileMeta> createFileMeta(Integer id) {
        return filesMetaRepo.findById(id);
    }
}
