package hse.kpo;

import hse.kpo.domains.Car;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.params.EmptyEngineParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandCarFactoryTest {
    @DisplayName("проверка что создаётся машина с правильным номером")
    @Test
    public void createCarTest() {
        HandCarFactory handCarFactory = new HandCarFactory();
        Car car = handCarFactory.createCar(EmptyEngineParams.DEFAULT, 1303);
        Assertions.assertEquals(1303, car.getVin());
    }
}
