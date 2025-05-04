package hse.kpo.factories;

import hse.kpo.domains.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerFactory {
    public Customer createCustomer(int customerId, String name, int handPower, int legPower, int iq) {
        return new Customer(customerId, name, handPower, legPower, iq);
    }
}
