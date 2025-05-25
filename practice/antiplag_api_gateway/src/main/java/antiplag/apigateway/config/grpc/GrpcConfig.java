package antiplag.apigateway.config.grpc;

import antiplag.fileAnalysisService.grpc.AnalysisServiceGrpc;
import antiplag.fileStoringService.grpc.StoringServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {
    @GrpcClient("StoringService")
    private StoringServiceGrpc.StoringServiceBlockingStub storingServiceStub;
    @Bean
    public StoringServiceGrpc.StoringServiceBlockingStub storingServiceStub() {
        return storingServiceStub;
    }

    @GrpcClient("AnalysisService")
    private AnalysisServiceGrpc.AnalysisServiceBlockingStub analysisServiceBlockingStub;
    @Bean
    public AnalysisServiceGrpc.AnalysisServiceBlockingStub analysisServiceStub() {
        return analysisServiceBlockingStub;
    }
}