package studying.factories;

import studying.domains.Car;
import studying.domains.ReviveEngine;
import studying.interfaces.ICarFactory;
import studying.params.EmptyEngineParams;

public class ReviveCarFactory implements ICarFactory<EmptyEngineParams> {
    @Override
    public Car createCar(EmptyEngineParams carParams, int carNumber) {
        var engine = new ReviveEngine(); // создаем двигатель на основе переданных параметров

        return new Car(carNumber, engine); // возвращаем собранный автомобиль с установленным двигателем и определенным номером
    }
}
