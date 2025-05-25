package antiplag.fileAnalysisService.adapters.inPorts.grpc;

import antiplag.fileAnalysisService.grpc.*;
import antiplag.fileStoringService.grpc.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@RequiredArgsConstructor
@GrpcService
public class AnalysisServiceInGrpcController  extends AnalysisServiceGrpc.AnalysisServiceImplBase {
    private final AnalyseFileController analyseFileController;
    private final WordCloudInController wordCloudInController;

    @Override
    public void getWordCloud(WordCloudRequest request, StreamObserver<WordCloudWrappedResponse> responseStreamObserver) {
        wordCloudInController.getWordCloud(request, responseStreamObserver);
    }

    @Override
    public void analyseFile(AnalyseRequest request, StreamObserver<AnalyseWrappedResponse> responseStreamObserver) {
        analyseFileController.analyseFile(request, responseStreamObserver);
    }
}
