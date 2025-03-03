package hse.kpo.factories.cars;

import hse.kpo.domains.Car;
import hse.kpo.domains.CatamaranWithWheels;
import hse.kpo.factories.catamarans.LevitationCatamaranFactory;
import hse.kpo.interfaces.cars.CarFactory;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Фабрика для создания машин по подобию катамаранов.
 */
@Component
public class LevitationCatamaranWithWheelsFactory implements CarFactory<EmptyEngineParams> {
    @Autowired
    LevitationCatamaranFactory levitationCatamaranFactory;

    @Override
    public Car create(EmptyEngineParams carParams, int carNumber) {
        return new CatamaranWithWheels(levitationCatamaranFactory.create(carParams, carNumber));
    }
}