package hse.kpo.service;

import hse.kpo.domains.Customer;
import hse.kpo.domains.Train;
import hse.kpo.enums.TrainTypes;
import hse.kpo.eventProcessorsI.ItrainingCompletedEventProcessor;
import hse.kpo.kafka.KafkaConsumer;
import hse.kpo.kafka.KafkaProducer;
import hse.kpo.repositories.IcustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingCenterService {
    private final IcustomerRepository customerRepository;
    private List<ItrainingCompletedEventProcessor> trainSubscribers = new ArrayList<>();

    public void addSubscriber(ItrainingCompletedEventProcessor trainingCompletedEventProcessor) {
        trainSubscribers.add(trainingCompletedEventProcessor);
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer trainCustomer(Train train) {
        var customer = train.customer().train(train.trainType());
        trainSubscribers.forEach(trainSubscriber -> trainSubscriber.onTrainingCompletedEvent(train));
        return customer;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
