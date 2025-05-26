package antiplag.apigateway.outAdapters.grpc.FileAnalysis;

import antiplag.apigateway.outAdapters.grpc.GrpcErrorHandler;
import antiplag.fileAnalysisService.grpc.AnalysisServiceGrpc;
import antiplag.apigateway.outAdapters.IfileAnalysisOutController;
import antiplag.apigateway.use_cases.dtos.requests.FileAnalyseRequest;
import antiplag.apigateway.use_cases.dtos.requests.WordCloudRequest;
import antiplag.apigateway.use_cases.dtos.responses.FileAnalyseResponse;
import antiplag.apigateway.use_cases.dtos.responses.WordCloudResponse;
import io.grpc.StatusRuntimeException;
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
    private final GrpcErrorHandler errorHandler;

    @Override
    public Optional<FileAnalyseResponse> analyseFile(FileAnalyseRequest request) {
        try {
            return responseMapping.getFileAnalyseResponse(
                    analysisServiceBlockingStub.analyseFile(
                            requestMapping.getAnalyseRequest(request)
                    )
            );
        } catch (StatusRuntimeException e) {
            errorHandler.handle(e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<WordCloudResponse> getWordCloud(WordCloudRequest request) {
        try {
            return responseMapping.getWordCloudResponse(
                    analysisServiceBlockingStub.getWordCloud(
                            requestMapping.getWordCloudRequest(request)
                    )
            );
        } catch (StatusRuntimeException e) {
            errorHandler.handle(e);
            return Optional.empty();
        }
    }
}
