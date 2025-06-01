package antiplag.fileAnalysisService.core.domain_services.factories;

import antiplag.fileAnalysisService.core.entities.AnalysationResult;
import antiplag.fileAnalysisService.core.entities.FileCloseness;
import antiplag.fileAnalysisService.ishared.ItimeManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AnalysationResultFactory {
    private final ItimeManager timeManager;

    public AnalysationResult createAnalysationResult(List<FileCloseness> nearestFiles, Integer fileId) {
        return new AnalysationResult(nearestFiles, timeManager.getCurLocalDate(), fileId);
    }
}
