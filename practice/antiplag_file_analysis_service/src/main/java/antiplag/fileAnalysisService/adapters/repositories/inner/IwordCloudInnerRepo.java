package antiplag.fileAnalysisService.adapters.repositories.inner;

import antiplag.fileAnalysisService.core.entities.WordCloud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;


@Component
public interface IwordCloudInnerRepo extends JpaRepository<WordCloud, Integer> {
    Optional<WordCloud> findByFileId(int FileId);
}
