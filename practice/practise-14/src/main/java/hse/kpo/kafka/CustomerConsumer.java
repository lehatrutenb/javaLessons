package hse.kpo.kafka;

import hse.kpo.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomerConsumer {
    @Autowired
    private CustomerService customerService;

    @EventListener
    @KafkaListener(topics = "training-updates", groupId = "kpo")
    public void handleTrainingUpdate(TrainingCompletedEvent event) {
        var customerOptional = customerService.findById(event.customerId());

        if (customerOptional.isEmpty()) {
            return;
        }

        var customer = customerOptional.get();
        switch (event.updatedParameter()) {
            case "handPower" -> customer.setHandPower(customer.getHandPower() + 1);
            case "legPower" -> customer.setLegPower(customer.getLegPower() + 1);
            case "iq" -> customer.setIq(customer.getIq() + 1);
            default -> {
                return;
            }
        }
        customerService.save(customer); // Сохраняем в БД
    }
}
