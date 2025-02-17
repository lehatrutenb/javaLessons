package hse.kpo.services;

import hse.kpo.domains.Customer;
import hse.kpo.interfaces.IcustomerProvider;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Класс для хранения покупателей сервиса.
 */
@Component
public class CustomerStorage implements IcustomerProvider {
    private List<Customer> customers = new ArrayList<>();

    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer); // просто добавляем покупателя в список
    }
}
