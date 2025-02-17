package hse.kpo.services;

import hse.kpo.interfaces.IcustomerProvider;
import java.util.Objects;

import hse.kpo.interfaces.IshipProvider;
import org.springframework.stereotype.Component;

/**
 * Класс объединяющий ICarProvider и ICustomerProvider и позволяющий распределять имеющиеся машины между покупателями.
 */
@Component
public class HseShipService {

    private final IshipProvider shipProvider;

    private final IcustomerProvider customerProvider;

    public HseShipService(IshipProvider shipProvider, IcustomerProvider customersProvider) {
        this.shipProvider = shipProvider;
        this.customerProvider = customersProvider;
    }

    /**
     * Метод итеруриется по покупателям и пытается сопоставить их с доступными машинами.
     */
    public void sellShips() {
        // получаем список покупателей
        var customers = customerProvider.getCustomers();
        // пробегаемся по полученному списку
        customers.stream().filter(customer -> Objects.isNull(customer.getCar()))
                .forEach(customer -> {
                    var ship = shipProvider.takeShip(customer);
                    if (Objects.nonNull(ship)) {
                        customer.setShip(ship);
                    }
                });
    }
}