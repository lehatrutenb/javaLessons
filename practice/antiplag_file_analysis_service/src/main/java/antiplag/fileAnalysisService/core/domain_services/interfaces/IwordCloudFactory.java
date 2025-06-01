package antiplag.fileAnalysisService.core.domain_services.interfaces;

import antiplag.fileAnalysisService.core.entities.WordCloud;
import antiplag.fileAnalysisService.core.entities.enums.ImageFormat;

import java.util.List;

public interface IwordCloudFactory {
    public WordCloud createWordCloud(Integer fileId, ImageFormat imageFormat, List<Byte> image);
}
