package hse.kpo.factories;

import hse.kpo.domains.Car;
import hse.kpo.domains.ReviveEngine;
import hse.kpo.interfaces.IcarFactory;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

/**
 * Класс фабрики создающей машины с ReviveEngine.
 */
@Component
public class ReviveCarFactory implements IcarFactory<EmptyEngineParams> {
    @Override
    public Car createCar(EmptyEngineParams carParams, int carNumber) {
        var engine = new ReviveEngine(); // создаем двигатель на основе переданных параметров

        return new Car(carNumber, engine); // возвращаем собранный автомобиль с заданным двигателем и данным номером
    }
}
