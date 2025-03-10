package hse.kpo.factories.cars;

import hse.kpo.domains.cars.Car;
import hse.kpo.domains.catamarans.Catamaran;
import hse.kpo.domains.cars.CatamaranWithWheels;
import org.springframework.stereotype.Component;

/**
 * Фабрика для создания машин по подобию катамаранов.
 */
@Component
public class CatamaranWithWheelsFactory {
    public Car create(Catamaran catamaran) {
        return new CatamaranWithWheels(catamaran);
    }
}
