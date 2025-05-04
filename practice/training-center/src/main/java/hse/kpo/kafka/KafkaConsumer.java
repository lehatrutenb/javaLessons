package hse.kpo.kafka;

import hse.kpo.facade.Facade;
import hse.kpo.service.TrainingCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {
    private final Facade facade;

    @KafkaListener(topics = "customers", groupId = "kpo2")
    public void handleCustomerAddedEvent(CustomerAddedEvent event) {
        facade.addCustomerEventProcess(event);
    }
}
