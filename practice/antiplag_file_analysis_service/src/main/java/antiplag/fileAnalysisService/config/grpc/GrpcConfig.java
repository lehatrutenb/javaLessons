package antiplag.fileAnalysisService.config.grpc;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import antiplag.fileStoringService.grpc.StoringServiceGrpc;

@Configuration
public class GrpcConfig {
    @GrpcClient("StoringService")
    private StoringServiceGrpc.StoringServiceBlockingStub storingServiceBlockingStub;
    @Bean
    public StoringServiceGrpc.StoringServiceBlockingStub storingServiceStub() {
        return storingServiceBlockingStub;
    }
}
