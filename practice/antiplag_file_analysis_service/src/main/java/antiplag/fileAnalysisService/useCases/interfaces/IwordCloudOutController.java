package antiplag.fileAnalysisService.useCases.interfaces;

import antiplag.fileAnalysisService.useCases.dtos.requests.GetWordCloudOutRequest;
import antiplag.fileAnalysisService.useCases.dtos.responses.GetWordCloudOutResponse;

public interface IwordCloudOutController {
    public GetWordCloudOutResponse createWordCloud(GetWordCloudOutRequest request);
}
