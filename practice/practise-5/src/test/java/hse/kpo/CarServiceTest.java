package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.services.CarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class CarServiceTest {
    @Autowired
    HandCarFactory handCarFactory;

    @Test
    @DisplayName("Добавляем Car и для наилучшего Customer получаем - хотим получить одинаковые")
    public void createCarGetTest() {
        Customer bestCustomer = new Customer("Leha", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        CarService carService = new CarService();
        carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

        Assert.notNull(carService.takeCar(bestCustomer), "expected to get car");
    }

    @Test
    @DisplayName("Добавляем Car и для наихудшего Customer хотим получить null")
    public void createCarNotGetTest() {
        Customer bestCustomer = new Customer("Leha", 0, 0, 0);
        CarService carService = new CarService();
        carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

        Assert.isNull(carService.takeCar(bestCustomer), "expected not to get car");
    }
}
