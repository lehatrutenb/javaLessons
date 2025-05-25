package antiplag.fileAnalysisService.useCases.interfaces;

import antiplag.fileAnalysisService.core.entities.WordCloud;

import java.util.Optional;

public interface IwordCloudRepo {
    public void save(WordCloud wordCloud);
    public Optional<WordCloud> findById(Integer id);
    public Optional<WordCloud> findByFileId(Integer id);
}
