package hse.kpo;

import hse.kpo.domains.Car;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PedalCarFactoryTest {
    @DisplayName("проверка что создаётся машина с правильным номером")
    @Test
    public void createCarTest() {
        PedalCarFactory pedalCarFactory = new PedalCarFactory();
        Car car = pedalCarFactory.createCar(new PedalEngineParams(10), 1303);
        Assertions.assertEquals(1303, car.getVin());
    }
}
