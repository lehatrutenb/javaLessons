package hse.kpo.kafka.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.kafka.CustomerAddedEvent;
import hse.kpo.kafka.KafkaProducerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class OutboxProcessor {
    private final OutboxEventRepository outboxEventRepository;
    private final KafkaProducerService kafkaProducerService;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 50_00)
    @Transactional
    public void process() {
        for (var event : outboxEventRepository.findAllBySentFalseOrderByCreatedAtAsc()) {
            try {
                kafkaProducerService.sendCustomerToTraining(objectMapper.readValue(event.getPayload(), CustomerAddedEvent.class));
                event.setSent(true);
                outboxEventRepository.save(event);
            } catch (Exception e) {
                log.warn(String.valueOf(e));
            }
        }
    }
}
