package antiplag.apigateway.outAdapters;

import antiplag.apigateway.use_cases.dtos.requests.FileAnalyseRequest;
import antiplag.apigateway.use_cases.dtos.requests.WordCloudRequest;
import antiplag.apigateway.use_cases.dtos.responses.FileAnalyseResponse;
import antiplag.apigateway.use_cases.dtos.responses.WordCloudResponse;

import java.util.Optional;

public interface IfileAnalysisOutController {
    public Optional<FileAnalyseResponse> analyseFile(FileAnalyseRequest request);
    public Optional<WordCloudResponse> getWordCloud(WordCloudRequest request);
}
