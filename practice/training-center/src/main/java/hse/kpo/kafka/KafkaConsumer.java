package hse.kpo.kafka;

import hse.kpo.facade.Facade;
import hse.kpo.service.TrainingCenterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {
    private final Facade facade;

    @EventListener // won't create class instance without it
    @KafkaListener(topics = "customers", groupId = "kpoTrainingCenter", errorHandler="kafkaConsumerErrorHandler")
    public void handleCustomerAddedEvent(CustomerAddedEvent event) {
        facade.addCustomerEventProcess(event);
    }
}
