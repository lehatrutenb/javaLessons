package antiplag.fileAnalysisService.useCases.dtos.innerComponents;

import antiplag.fileAnalysisService.core.domain_services.factories.WordCloudFactory;
import antiplag.fileAnalysisService.core.domain_services.interfaces.IwordCloudFactory;
import antiplag.fileAnalysisService.core.entities.File;
import antiplag.fileAnalysisService.core.entities.WordCloud;
import antiplag.fileAnalysisService.core.entities.enums.ImageFormat;
import antiplag.fileAnalysisService.useCases.dtos.requests.GetWordCloudOutRequest;
import antiplag.fileAnalysisService.useCases.interfaces.IwordCloudCreator;
import antiplag.fileAnalysisService.useCases.interfaces.IwordCloudOutController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WordCloudCreator implements IwordCloudCreator {
    private final IwordCloudOutController wordCloudOutController;
    private final IwordCloudFactory wordCloudFactory;

    @Override
    public WordCloud createWordCloud(File file) {
        var response =
                wordCloudOutController.createWordCloud(new GetWordCloudOutRequest(file.getBody(), "png"));
        return wordCloudFactory.createWordCloud(file.getId(), ImageFormat.PNG, response.rawImg());
    }
}
