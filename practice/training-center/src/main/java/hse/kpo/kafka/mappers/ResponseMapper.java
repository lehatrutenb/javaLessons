package hse.kpo.kafka.mappers;

import hse.kpo.controllers.CustomerResponse;
import hse.kpo.domains.Customer;
import hse.kpo.domains.Train;
import hse.kpo.kafka.TrainingCompletedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

// not bad idea to divide them but if it has < 50 string - no real reason
@Component
public class ResponseMapper {
    public TrainingCompletedEvent Train2TrainingCompletedEvent(Train train) {
        return new TrainingCompletedEvent(train.customer().getId(), train.trainType().toString());
    }
    public CustomerResponse Customer2CustomerResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(), customer.getName(), customer.getHandPower(),
                customer.getLegPower(), customer.getIq()
                );
    }
    public List<CustomerResponse> Customer2CustomerResponse(List<Customer> customers) {
        return customers.stream().map(this::Customer2CustomerResponse).toList();
    }
}
