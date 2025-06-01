package antiplag.fileAnalysisService.useCases.services;

import antiplag.fileAnalysisService.core.domain_services.factories.AnalysationResultFactory;
import antiplag.fileAnalysisService.core.domain_services.interfaces.IfileAnalyzer;
import antiplag.fileAnalysisService.core.entities.AnalysationResult;
import antiplag.fileAnalysisService.core.entities.File;
import antiplag.fileAnalysisService.useCases.interfaces.IanalysationResultRepo;
import antiplag.fileAnalysisService.useCases.interfaces.IfilesLoadInnerOutController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileAnalyseService {
    private final IfilesLoadInnerOutController filesLoader;
    private final IfileAnalyzer fileAnalyzer;
    private final AnalysationResultFactory analysationResultFactory;
    private final IanalysationResultRepo analysationResultRepo;

    @Cacheable(value = "analystationResults", key = "#fileId", condition="!#forceAnalyze")
    @CachePut(value = "analystationResults", key = "#fileId", condition="#forceAnalyze") // to put on force
    public AnalysationResult AnalyseFile(Integer fileId, boolean forceAnalyze) {
        log.debug(String.format("analyzing file with id: %d", fileId));
        List<Integer> allFiles = new ArrayList<>(filesLoader.getStoringFilesIDs());
        if (allFiles.isEmpty()) {
            return analysationResultFactory.createAnalysationResult(new ArrayList<>(), fileId);
        }
        allFiles.add(fileId);
        List<Optional<File>> files = new ArrayList<>(filesLoader.loadFiles(allFiles.reversed()));
        if (files.getFirst().isEmpty()) {
            throw new IllegalArgumentException("failed to load file to analyze");
        }

        File fileToCheck = files.getFirst().get();
        files.removeFirst();
        var analysationResult = fileAnalyzer.getClosestFiles(fileToCheck,
                files.stream().filter(Optional::isPresent).map(Optional::get).toList());
        analysationResultRepo.save(analysationResult);
        return analysationResult;
    }
}
