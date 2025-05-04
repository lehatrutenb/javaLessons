package hse.kpo.facade;

import hse.kpo.controllers.CustomerResponse;
import hse.kpo.controllers.TrainRequest;
import hse.kpo.domains.Customer;
import hse.kpo.domains.Train;
import hse.kpo.kafka.CustomerAddedEvent;
import hse.kpo.kafka.KafkaProducer;
import hse.kpo.kafka.mappers.RequestMapper;
import hse.kpo.kafka.mappers.ResponseMapper;
import hse.kpo.repositories.IcustomerRepository;
import hse.kpo.service.TrainingCenterService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
// @RequiredArgsConstructor
public class Facade {
    @Autowired
    private TrainingCenterService trainingCenterService;
    @Autowired
    private RequestMapper requestMapper;
    @Autowired
    private ResponseMapper responseMapper;
    @Autowired
    private KafkaProducer kafkaProducer;
    @Autowired
    private IcustomerRepository customerRepository;

    public void addCustomerEventProcess(CustomerAddedEvent customerAddedEvent) {
        trainingCenterService.addCustomer(requestMapper.CustomerAddedEvent2Customer(customerAddedEvent));
    }

    @PostConstruct
    private void addSubscribers() {
        trainingCenterService.addSubscriber(kafkaProducer);
    }

    public void trainCustomer(TrainRequest trainRequest) {
        var train = requestMapper.TrainRequest2Train(trainRequest);
        if (train.isEmpty()) {
            throw new IllegalArgumentException("can't map trainRequest to domain model");
        }
        // customerRepository.delete(train.get().customer()); am i really have to delete there
        customerRepository.save(trainingCenterService.trainCustomer(train.get()));
    }

    public List<CustomerResponse> getCustomers() {
        return responseMapper.Customer2CustomerResponse(trainingCenterService.getCustomers());
    }
}
