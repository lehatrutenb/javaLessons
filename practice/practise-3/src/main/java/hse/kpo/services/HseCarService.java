package hse.kpo.services;

import hse.kpo.interfaces.ICarProvider;
import hse.kpo.interfaces.ICustomerProvider;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class HseCarService {

    private final ICarProvider carProvider;

    private final ICustomerProvider customerProvider;

    public HseCarService(ICarProvider carProvider, ICustomerProvider customersProvider)
    {
        this.carProvider = carProvider;
        this.customerProvider = customersProvider;
    }

    public void sellCars()
    {
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