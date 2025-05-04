package hse.kpo.kafka.mappers;

import hse.kpo.controllers.TrainRequest;
import hse.kpo.domains.Customer;
import hse.kpo.domains.Train;
import hse.kpo.enums.TrainTypes;
import hse.kpo.factories.CustomerFactory;
import hse.kpo.kafka.CustomerAddedEvent;
import hse.kpo.repositories.IcustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RequestMapper {
    private final IcustomerRepository customerRepository;
    private final CustomerFactory customerFactory;
    public Customer CustomerAddedEvent2Customer(CustomerAddedEvent event) {
        return customerFactory.createCustomer(
                event.customerId(),
                event.customerName(),
                event.handPower(),
                event.legPower(),
                event.iq()
                );
    }

    public Optional<Train> TrainRequest2Train(TrainRequest trainRequest) {
        var customer = customerRepository.findById(trainRequest.customerId());
        var train = TrainTypes.get(trainRequest.trainType());
        if (customer.isEmpty() || train.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new Train(customer.get(), train.get()));
    }
}
