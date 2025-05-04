package hse.kpo.kafka;

import hse.kpo.domains.Train;
import hse.kpo.eventProcessorsI.ItrainingCompletedEventProcessor;
import hse.kpo.kafka.mappers.RequestMapper;
import hse.kpo.kafka.mappers.ResponseMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class KafkaProducer implements ItrainingCompletedEventProcessor {
    private final ResponseMapper responseMapper;
    private final KafkaTemplate<String, TrainingCompletedEvent> kafkaTemplate;

    @Override
    public void onTrainingCompletedEvent(Train train) {
        TrainingCompletedEvent event = responseMapper.Train2TrainingCompletedEvent(train);
        kafkaTemplate.send("training-updates", UUID.randomUUID().toString(), event); // gen random uuid cause not need it in other sevices
    }
}
