package payment.apigateway.config;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import payment.apigateway.grpc.OrderServiceGrpc;
import payment.apigateway.grpc.PaymentServiceGrpc;

@Configuration
public class GrpcConfig {
    @GrpcClient("OrderService") // name from proto
    private OrderServiceGrpc.OrderServiceBlockingStub orderServiceStub;
    @Bean
    public OrderServiceGrpc.OrderServiceBlockingStub orderServiceStub() {
        return orderServiceStub;
    }

    @GrpcClient("PaymentService") // name from proto
    private PaymentServiceGrpc.PaymentServiceBlockingStub paymentServiceStub;
    @Bean
    public PaymentServiceGrpc.PaymentServiceBlockingStub paymentServiceStub() {
        return paymentServiceStub;
    }
}