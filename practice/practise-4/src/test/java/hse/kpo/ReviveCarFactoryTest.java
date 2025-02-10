package hse.kpo;

import hse.kpo.domains.Car;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.ReviveCarFactory;
import hse.kpo.params.EmptyEngineParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReviveCarFactoryTest {
    @DisplayName("проверка что создаётся машина с правильным номером")
    @Test
    public void createCarTest() {
        ReviveCarFactory reviveCarFactory = new ReviveCarFactory();
        Car car = reviveCarFactory.createCar(EmptyEngineParams.DEFAULT, 1303);
        Assertions.assertEquals(1303, car.getVin());
    }
}
