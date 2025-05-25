package antiplag.fileAnalysisService.useCases.dtos.mappers;

import antiplag.fileAnalysisService.core.entities.WordCloud;
import antiplag.fileAnalysisService.useCases.dtos.responses.GetWordCloudInResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

@Component
public class WordCloudInMapper {
    public GetWordCloudInResponse wordCloudToWordCloudInResponse(WordCloud wordCloud) throws JsonProcessingException {
        return new GetWordCloudInResponse(wordCloud.getImg(), wordCloud.getImageFormat().toString());
    }
}
