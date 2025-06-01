package antiplag.fileAnalysisService.adapters.outPorts;

import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GrpcErrorHandler {
    public void handle(StatusRuntimeException e) { // deepseeked
        switch (e.getStatus().getCode()) {
            case UNAVAILABLE:
                log.error("Service unavailable: {}", e.getMessage());
                break;
            case DEADLINE_EXCEEDED:
                log.error("Timeout calling service: {}", e.getMessage());
                break;
            case PERMISSION_DENIED:
                log.error("Permission denied: {}", e.getMessage());
                break;
            default:
                log.error("gRPC error: {}", e.getMessage());
        }
    }
}