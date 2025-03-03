package hse.kpo.factories.cars;

import hse.kpo.domains.Car;
import hse.kpo.domains.CatamaranWithWheels;
import hse.kpo.factories.catamarans.PedalCatamaranFactory;
import hse.kpo.interfaces.cars.CarFactory;
import hse.kpo.params.PedalEngineParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Фабрика для создания машин по подобию катамаранов.
 */
@Component
public class PedalCatamaransWithWheelsFactory implements CarFactory<PedalEngineParams> {
    @Autowired
    PedalCatamaranFactory pedalCatamaranFactory;

    @Override
    public Car create(PedalEngineParams carParams, int carNumber) {
        return new CatamaranWithWheels(pedalCatamaranFactory.create(carParams, carNumber));
    }
}