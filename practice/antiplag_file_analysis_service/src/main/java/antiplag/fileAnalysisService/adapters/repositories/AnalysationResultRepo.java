package antiplag.fileAnalysisService.adapters.repositories;

import antiplag.fileAnalysisService.adapters.repositories.inner.IanalysationResultInnerRepo;
import antiplag.fileAnalysisService.core.entities.AnalysationResult;
import antiplag.fileAnalysisService.useCases.interfaces.IanalysationResultRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AnalysationResultRepo implements IanalysationResultRepo {
    private final IanalysationResultInnerRepo analysationResultInnerRepo;

    @Override
    public void save(AnalysationResult analysationResult) {
        analysationResultInnerRepo.save(analysationResult);
    }

    @Override
    public Optional<AnalysationResult> findById(Integer id) {
        return analysationResultInnerRepo.findById(id);
    }

    @Override
    public Optional<AnalysationResult> findLatestByFileId(Integer fileId) {
        return analysationResultInnerRepo.findFirstByFileIdOrderByDateCreatedDesc(fileId);
    }
}
