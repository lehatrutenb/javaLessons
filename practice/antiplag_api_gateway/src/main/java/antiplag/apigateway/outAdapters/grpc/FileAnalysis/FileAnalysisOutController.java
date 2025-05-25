package antiplag.apigateway.outAdapters.grpc.FileAnalysis;

import antiplag.fileAnalysisService.grpc.AnalysisServiceGrpc;
import antiplag.apigateway.outAdapters.IfileAnalysisOutController;
import antiplag.apigateway.use_cases.dtos.requests.FileAnalyseRequest;
import antiplag.apigateway.use_cases.dtos.requests.WordCloudRequest;
import antiplag.apigateway.use_cases.dtos.responses.FileAnalyseResponse;
import antiplag.apigateway.use_cases.dtos.responses.WordCloudResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class FileAnalysisOutController implements IfileAnalysisOutController {
    private final AnalysisServiceGrpc.AnalysisServiceBlockingStub analysisServiceBlockingStub;
    private final GrpcFileAnalysisRequestMapping requestMapping;
    private final GrpcFileAnalysisResponseMapping responseMapping;


    @Override
    public Optional<FileAnalyseResponse> analyseFile(FileAnalyseRequest request) {
        return responseMapping.getFileAnalyseResponse(
                analysisServiceBlockingStub.analyseFile(
                        requestMapping.getAnalyseRequest(request)
                )
        );
    }

    @Override
    public Optional<WordCloudResponse> getWordCloud(WordCloudRequest request) {
        return responseMapping.getWordCloudResponse(
                analysisServiceBlockingStub.getWordCloud(
                        requestMapping.getWordCloudRequest(request)
                )
        );
    }
}
