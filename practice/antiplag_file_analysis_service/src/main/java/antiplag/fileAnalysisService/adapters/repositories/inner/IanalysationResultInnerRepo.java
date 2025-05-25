package antiplag.fileAnalysisService.adapters.repositories.inner;

import antiplag.fileAnalysisService.core.entities.AnalysationResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IanalysationResultInnerRepo extends JpaRepository<AnalysationResult, Integer> {
    Optional<AnalysationResult> findFirstByFileIdOrderByDateCreatedDesc(int FileId);
}
