package hse.kpo.services;

import hse.kpo.interfaces.IcarProvider;
import hse.kpo.interfaces.IcustomerProvider;
import java.util.Objects;
import org.springframework.stereotype.Component;

/**
 * Класс объединяющий ICarProvider и ICustomerProvider и позволяющий распределять имеющиеся машины между покупателями.
 */
@Component
public class HseCarService {

    private final IcarProvider carProvider;

    private final IcustomerProvider customerProvider;

    public HseCarService(IcarProvider carProvider, IcustomerProvider customersProvider) {
        this.carProvider = carProvider;
        this.customerProvider = customersProvider;
    }

    /**
     * Метод итеруриется по покупателям и пытается сопоставить их с доступными машинами.
     */
    public void sellCars() {
        // получаем список покупателей
        var customers = customerProvider.getCustomers();
        // пробегаемся по полученному списку
        customers.stream().filter(customer -> Objects.isNull(customer.getCar()))
                .forEach(customer -> {
                    var car = carProvider.takeCar(customer);
                    if (Objects.nonNull(car)) {
                        customer.setCar(car);
                    }
                });
    }
}