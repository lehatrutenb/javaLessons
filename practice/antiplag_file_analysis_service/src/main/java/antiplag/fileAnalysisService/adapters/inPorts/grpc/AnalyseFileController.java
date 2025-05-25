package antiplag.fileAnalysisService.adapters.inPorts.grpc;

import antiplag.fileAnalysisService.grpc.*;
import antiplag.fileAnalysisService.useCases.Ifacade;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AnalyseFileController {
    private final Ifacade facade;
    private final AnalyseFileMapper analyseFileMapper;

    public void analyseFile(AnalyseRequest request, StreamObserver<AnalyseWrappedResponse> responseStreamObserver) {
        log.debug(String.format("Got request to analyze file: id=%d forceRecalc=%b", request.getId(), request.getForceRecalc()));
        AnalyseWrappedResponse response = analyseFileMapper.getAnalyseWrappedResponse(
                facade.analyseFile(analyseFileMapper.getFileAnalyseRequest(request))
            );

        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }
}
