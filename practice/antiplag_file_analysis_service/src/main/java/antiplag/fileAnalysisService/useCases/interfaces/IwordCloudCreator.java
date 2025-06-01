package antiplag.fileAnalysisService.useCases.interfaces;

import antiplag.fileAnalysisService.core.entities.File;
import antiplag.fileAnalysisService.core.entities.WordCloud;

public interface IwordCloudCreator {
    public WordCloud createWordCloud(File file);
}
