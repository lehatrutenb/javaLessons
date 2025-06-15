package payment.paymentService.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig<K, V> { // not changes default configs
    private final KafkaProperties kafkaProperties; // to load default kafka config from spring

    public ConsumerFactory<K, V> fastConsumerFactory() {
        Map<String, Object> config = kafkaProperties.buildConsumerProperties();
        return new DefaultKafkaConsumerFactory<>(config);
    }

    public ConsumerFactory<K, V> reliableConsumerFactory() {
        Map<String, Object> config = kafkaProperties.buildConsumerProperties();
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    @Qualifier("fastKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<K, V> fastKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<K, V> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(fastConsumerFactory());
        return factory;
    }

    @Bean
    @Qualifier("reliableKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<K, V> reliableKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<K, V> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(reliableConsumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        factory.getContainerProperties().setSyncCommits(true);
        return factory;
    }
}
