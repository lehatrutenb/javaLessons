package hse.kpo.interfaces;

import hse.kpo.domains.Customer;
import java.util.List;

/**
 * Интерфейс, который должны предоставлять классы содержащие покупателей.
 */
public interface IcustomerProvider {
    /**
     * Метод для получения сохранённых кастомеров и их полей.
     * метод возвращает коллекцию только для чтения, так как
     * мы не хотим давать вызывающему коду возможность изменять список
     *
     * @return список имеющихся Customer
     */
    List<Customer> getCustomers();
}
