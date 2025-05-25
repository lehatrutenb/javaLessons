package antiplag.fileAnalysisService.useCases.interfaces;

import antiplag.fileAnalysisService.core.entities.AnalysationResult;
import antiplag.fileAnalysisService.core.entities.WordCloud;

import java.util.Optional;

public interface IanalysationResultRepo {
    public void save(AnalysationResult analysationResult);
    public Optional<AnalysationResult> findById(Integer id);
    public Optional<AnalysationResult> findLatestByFileId(Integer fileId);
}