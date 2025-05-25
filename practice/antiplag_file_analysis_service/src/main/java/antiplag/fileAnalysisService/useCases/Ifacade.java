package antiplag.fileAnalysisService.useCases;

import antiplag.fileAnalysisService.useCases.dtos.requests.FileAnalyseRequest;
import antiplag.fileAnalysisService.useCases.dtos.requests.GetWordCloudInRequest;
import antiplag.fileAnalysisService.useCases.dtos.responses.FileAnalyseResponse;
import antiplag.fileAnalysisService.useCases.dtos.responses.GetWordCloudInResponse;

public interface Ifacade {
    public GetWordCloudInResponse getWordCloud(GetWordCloudInRequest request);
    public FileAnalyseResponse analyseFile(FileAnalyseRequest request);
}
