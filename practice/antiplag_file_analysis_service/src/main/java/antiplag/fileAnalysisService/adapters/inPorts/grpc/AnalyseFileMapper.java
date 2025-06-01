package antiplag.fileAnalysisService.adapters.inPorts.grpc;

import antiplag.fileAnalysisService.grpc.AnalyseRequest;
import antiplag.fileAnalysisService.grpc.AnalyseResponse;
import antiplag.fileAnalysisService.grpc.AnalyseWrappedResponse;
import antiplag.fileAnalysisService.grpc.FileCloseness;
import antiplag.fileAnalysisService.useCases.dtos.requests.FileAnalyseRequest;
import antiplag.fileAnalysisService.useCases.dtos.responses.FileAnalyseResponse;
import antiplag.fileAnalysisService.useCases.dtos.responses.FileClosenessResponse;
import org.springframework.stereotype.Component;

@Component
public class AnalyseFileMapper {
    public FileAnalyseRequest getFileAnalyseRequest(AnalyseRequest request) {
        return new FileAnalyseRequest(request.getId(), request.getForceRecalc());
    }

    private FileCloseness getFileCloseness(FileClosenessResponse closeness) {
        return FileCloseness.newBuilder()
                .setId(closeness.id())
                .setName(closeness.name())
                .setClosenessRank(closeness.closenessRank()).build();
    }

    public AnalyseWrappedResponse getAnalyseWrappedResponse(FileAnalyseResponse response) {
        return AnalyseWrappedResponse.newBuilder().setResponse(
                AnalyseResponse.newBuilder().addAllClosestFiles(
                    response.nearestFiles().stream().map(this::getFileCloseness).toList()
                ).build()
        ).build();
    }
}
