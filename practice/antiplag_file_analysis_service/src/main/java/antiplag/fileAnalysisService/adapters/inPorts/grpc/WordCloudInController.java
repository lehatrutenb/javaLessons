package antiplag.fileAnalysisService.adapters.inPorts.grpc;

import antiplag.fileStoringService.grpc.*;
import antiplag.fileAnalysisService.grpc.*;
import antiplag.fileAnalysisService.useCases.Ifacade;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WordCloudInController {
    private final Ifacade facade;
    private final WordCloudInGrpcMapper mapper;

    public void getWordCloud(WordCloudRequest request, StreamObserver<WordCloudWrappedResponse> responseStreamObserver) {
        responseStreamObserver.onNext(
            mapper.getWordCloudWrappedResponse(
                    facade.getWordCloud(
                        mapper.WordCloudRequestToGrtWordCloudInRequest(request)
                    )
            )
        );
        responseStreamObserver.onCompleted();
    }
}
