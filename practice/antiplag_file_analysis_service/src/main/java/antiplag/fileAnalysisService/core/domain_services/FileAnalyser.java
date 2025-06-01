package antiplag.fileAnalysisService.core.domain_services;

import antiplag.fileAnalysisService.core.domain_services.factories.AnalysationResultFactory;
import antiplag.fileAnalysisService.core.domain_services.interfaces.IfileAnalyzer;
import antiplag.fileAnalysisService.core.entities.AnalysationResult;
import antiplag.fileAnalysisService.core.entities.File;
import antiplag.fileAnalysisService.core.entities.FileCloseness;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FileAnalyser implements IfileAnalyzer {
    private final AnalysationResultFactory analysationResultFactory;
    private final Integer maxFileInResponseAmount = 5;

    private FileCloseness calcCloseness(File fMain, File fWeak) {
        if (fMain.getBody().equals(fWeak.getBody())) {
            return new FileCloseness(fWeak, 1);
        }
        return new FileCloseness(fWeak, 0);
    }

    @Override
    public AnalysationResult getClosestFiles(File fToCheck, List<File> files) {
        return analysationResultFactory.createAnalysationResult(
                files.stream().map(file -> calcCloseness(fToCheck, file))
                        .sorted(Comparator.comparing(FileCloseness::rank).reversed())
                        .limit(maxFileInResponseAmount).toList(),
                fToCheck.getId()
        );
    }
}
