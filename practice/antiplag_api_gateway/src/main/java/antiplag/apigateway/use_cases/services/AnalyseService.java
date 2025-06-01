package antiplag.apigateway.use_cases.services;

import antiplag.apigateway.outAdapters.IfileAnalysisOutController;
import antiplag.apigateway.use_cases.dtos.requests.FileAnalyseRequest;
import antiplag.apigateway.use_cases.dtos.requests.WordCloudRequest;
import antiplag.apigateway.use_cases.dtos.responses.FileAnalyseResponse;
import antiplag.apigateway.use_cases.dtos.responses.WordCloudResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalyseService {
    private final IfileAnalysisOutController fileAnalysisOutController;
    public FileAnalyseResponse analyseFile(FileAnalyseRequest fileAnalyseRequest) {
        var response = fileAnalysisOutController.analyseFile(fileAnalyseRequest);
        if (response.isEmpty()) {
            throw new IllegalArgumentException("no such file found");
        }
        return response.get();
    }

    public WordCloudResponse getWordCloud(WordCloudRequest wordCloudRequest) {
        var response = fileAnalysisOutController.getWordCloud(wordCloudRequest);
        if (response.isEmpty()) {
            throw new IllegalArgumentException("no such file found");
        }
        return response.get();
    }
}
