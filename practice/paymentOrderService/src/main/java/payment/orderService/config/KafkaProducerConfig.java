package payment.orderService.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig<K, V> { // not changes default configs
    private final KafkaProperties kafkaProperties; // to load default kafka config from spring

    public ProducerFactory<K, V> fastProducerFactory() {
        Map<String, Object> config = kafkaProperties.buildProducerProperties();
        return new DefaultKafkaProducerFactory<>(config);
    }

    public ProducerFactory<K, V> reliableProducerFactory() {
        Map<String, Object> config = kafkaProperties.buildProducerProperties();
        config.put(ProducerConfig.ACKS_CONFIG, "all");
        return new DefaultKafkaProducerFactory<>(config);
    }

    public KafkaTemplate<K, V> fastKafkaTemplate() {
        return new KafkaTemplate<K, V>(fastProducerFactory());
    }

    public KafkaTemplate<K, V> reliableKafkaTemplate() {
        return new KafkaTemplate<K, V>(reliableProducerFactory());
    }
}
