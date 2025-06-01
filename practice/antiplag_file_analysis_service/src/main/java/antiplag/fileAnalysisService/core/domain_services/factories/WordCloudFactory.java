package antiplag.fileAnalysisService.core.domain_services.factories;

import antiplag.fileAnalysisService.core.domain_services.interfaces.IwordCloudFactory;
import antiplag.fileAnalysisService.core.entities.WordCloud;
import antiplag.fileAnalysisService.core.entities.enums.ImageFormat;
import antiplag.fileAnalysisService.ishared.ItimeManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WordCloudFactory implements IwordCloudFactory {
    private final ItimeManager timeManager;

    public WordCloud createWordCloud(Integer fileId, ImageFormat imageFormat, List<Byte> image) {
        return new WordCloud(fileId, imageFormat, image);
    }
}
