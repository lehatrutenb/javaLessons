package antiplag.apigateway.outAdapters.grpc.FileAnalysis;

import antiplag.fileAnalysisService.grpc.AnalyseRequest;
import antiplag.fileAnalysisService.grpc.WordCloudRequest;
import antiplag.apigateway.use_cases.dtos.requests.FileAnalyseRequest;
import org.springframework.stereotype.Component;

@Component
public class GrpcFileAnalysisRequestMapping {
    public AnalyseRequest getAnalyseRequest(FileAnalyseRequest request) {
        return AnalyseRequest.newBuilder().setId(request.id()).setForceRecalc(request.forceRecalc()).build();
    }

    public WordCloudRequest getWordCloudRequest(antiplag.apigateway.use_cases.dtos.requests.WordCloudRequest request) {
        return WordCloudRequest.newBuilder().setId(request.id()).build();
    }
}
