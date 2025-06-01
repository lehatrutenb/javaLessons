package antiplag.fileAnalysisService.adapters.repositories;

import antiplag.fileAnalysisService.adapters.repositories.inner.IwordCloudInnerRepo;
import antiplag.fileAnalysisService.core.entities.WordCloud;
import antiplag.fileAnalysisService.useCases.interfaces.IwordCloudRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WordCloudRepo implements IwordCloudRepo {
    private final IwordCloudInnerRepo wordCloudRepo;

    @Override
    public void save(WordCloud wordCloud) {
        wordCloudRepo.save(wordCloud);
    }

    @Override
    public Optional<WordCloud> findById(Integer id) {
        return wordCloudRepo.findById(id);
    }

    @Override
    public Optional<WordCloud> findByFileId(Integer id) {
        return wordCloudRepo.findByFileId(id);
    }
}
