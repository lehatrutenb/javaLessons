package antiplag.fileAnalysisService.useCases.services;

import antiplag.fileAnalysisService.core.entities.File;
import antiplag.fileAnalysisService.core.entities.WordCloud;
import antiplag.fileAnalysisService.useCases.interfaces.IwordCloudCreator;
import antiplag.fileAnalysisService.useCases.interfaces.IwordCloudRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WordCloudService {
    private final IwordCloudCreator wordCloudCreator;
    private final IwordCloudRepo wordCloudRepo;

    public WordCloud getWordCloud(File file) {
        {
            Optional<WordCloud> wordCloud = wordCloudRepo.findByFileId(file.getId());
            if (wordCloud.isPresent()) {
                return wordCloud.get();
            }
        }
        log.debug(String.format("generating word cloud for file with id=%d", file.getId()));
        WordCloud wordCloud = wordCloudCreator.createWordCloud(file);
        wordCloudRepo.save(wordCloud);
        return wordCloud;
    }
}
