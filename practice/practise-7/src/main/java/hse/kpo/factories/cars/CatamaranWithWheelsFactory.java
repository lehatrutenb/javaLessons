package hse.kpo.factories.cars;

import hse.kpo.domains.Car;
import hse.kpo.domains.Catamaran;
import hse.kpo.domains.CatamaranWithWheels;
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
