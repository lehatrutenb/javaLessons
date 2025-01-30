package hse.kpo.factories;

import hse.kpo.domains.Car;
import hse.kpo.domains.ReviveEngine;
import hse.kpo.interfaces.ICarFactory;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

@Component
public class ReviveCarFactory implements ICarFactory<EmptyEngineParams> {
    @Override
    public Car createCar(EmptyEngineParams carParams, int carNumber) {
        var engine = new ReviveEngine(); // создаем двигатель на основе переданных параметров

        return new Car(carNumber, engine); // возвращаем собранный автомобиль с установленным двигателем и определенным номером
    }
}
