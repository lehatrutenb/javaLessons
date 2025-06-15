package payment.paymentService.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@ConfigurationProperties(prefix = "kafka")
@Getter
@Setter
@EnableKafka
public class KafkaConfig {
    private OrderAppliement orderAppliement;

    @Getter
    @Setter
    public static class OrderAppliement {
        private String topicRequests;
        private String topicResponses;
        private String groupId;
    }
}
