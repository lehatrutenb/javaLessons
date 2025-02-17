package hse.kpo;

import hse.kpo.domains.Car;
import hse.kpo.domains.Customer;
import hse.kpo.domains.ReviveEngine;
import hse.kpo.factories.*;
import hse.kpo.interfaces.IcarFactory;
import hse.kpo.interfaces.IcarProvider;
import hse.kpo.interfaces.IcustomerProvider;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
class ApplicationTests {
    @Autowired
    CarService carService;
    @Autowired
    ShipService shipService;
    @Autowired
    CustomerStorage customerStorage;
    @Autowired
    HseCarService hseCarService;
    @Autowired
    HseShipService hseShipService;
    @Autowired
    HandCarFactory handCarFactory;
    @Autowired
    HandShipFactory handShipFactory;
    @Autowired
    PedalCarFactory pedalCarFactory;
    @Autowired
    PedalShipFactory pedalShipFactory;
    @Autowired
    ReviveCarFactory reviveCarFactory;
    @Autowired
    ReviveShipFactory reviveShipFactory;
    @Mock
    IcarFactory<hse.kpo.params.EmptyEngineParams> mockCarFactoryEmptyEngine;
    @Mock
    IcustomerProvider mockCustomerProvider;
    @Spy
    IcarProvider spyCarProvider;

    private static <T> boolean uniqueListsAreEqual(List<T> list1, List<T> list2) {
        return list1.containsAll(list2) && list2.containsAll(list1);
    }

    @DisplayName("Тест логики создания кораблей")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    @Test
    public void testShip() {
        shipService.addShip(reviveShipFactory, EmptyEngineParams.DEFAULT);
        shipService.addShip(handShipFactory, EmptyEngineParams.DEFAULT);

        Customer customer1 = new Customer("1", 0, 0, 301);
        customerStorage.addCustomer(customer1);

        hseShipService.sellShips();

        Assertions.assertEquals(customer1.getShip().getVin(), 1);
    }

    @DisplayName("Тест логики ливитирующих двигателей DI1")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    @Test
    public void testReviveCar() {
        carService.addCar(reviveCarFactory, EmptyEngineParams.DEFAULT);
        carService.addCar(reviveCarFactory, EmptyEngineParams.DEFAULT);

        Customer customer1 = new Customer("1", 0, 0, 300);
        Customer customer2 = new Customer("1", 0, 0, 301);
        Customer customer3 = new Customer("1", 0, 0, -301);
        customerStorage.addCustomer(customer1);
        customerStorage.addCustomer(customer2);
        customerStorage.addCustomer(customer3);

        hseCarService.sellCars();

        List<Customer> expected = Arrays.asList(customer1, customer3);
        List<Customer> got = customerStorage.getCustomers().stream().filter(
                customer -> Objects.isNull(customer.getCar())).toList();
        //Assertions.assertEquals(expected, got);

        if (!uniqueListsAreEqual(got, expected)) {
            throw new RuntimeException("assertion failed");
        }
    }

    @DisplayName("Тест логики ливитирующих двигателей совместно с остальными двигателями DI2")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    @Test
    public void testReviveAndOthers() {
        carService.addCar(reviveCarFactory, EmptyEngineParams.DEFAULT);
        carService.addCar(reviveCarFactory, EmptyEngineParams.DEFAULT);
        carService.addCar(pedalCarFactory, new PedalEngineParams(10));
        carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

        Customer customer1 = new Customer("1", 0, 0, 301);
        Customer customer2 = new Customer("1", 10, 10, 300);
        Customer customer3 = new Customer("1", 10, 10, -301);
        Customer customer4 = new Customer("1", 0, 0, (int) 2e9);
        customerStorage.addCustomer(customer1);
        customerStorage.addCustomer(customer2);
        customerStorage.addCustomer(customer3);
        customerStorage.addCustomer(customer4);

        hseCarService.sellCars();

        List<Customer> expected = Arrays.asList();
        List<Customer> got = customerStorage.getCustomers().stream().filter(
                customer -> Objects.isNull(customer.getCar())).toList();

        if (!uniqueListsAreEqual(got, expected)) {
            throw new RuntimeException("assertion failed");
        }
    }

    @DisplayName("Smoke тест базовой логики")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    @Test
    public void defaultTest() {
        Customer customerNotGetCar = new Customer("Bob4", 4, 4);
        customerStorage.addCustomer(new Customer("Bob1", 6, 4));
        customerStorage.addCustomer(new Customer("Bob2", 4, 6));
        customerStorage.addCustomer(new Customer("Bob3", 6, 6));
        customerStorage.addCustomer(customerNotGetCar);

        carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);
        carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

        carService.addCar(pedalCarFactory, new PedalEngineParams(10));
        carService.addCar(pedalCarFactory, new PedalEngineParams(10));

        System.out.println(customerStorage.getCustomers());

        hseCarService.sellCars();

        System.out.println(customerStorage.getCustomers()); // got expected output

        List<Customer> expected = Arrays.asList(customerNotGetCar);
        List<Customer> got = customerStorage.getCustomers().stream().filter(
                customer -> Objects.isNull(customer.getCar())).toList();

        if (!uniqueListsAreEqual(got, expected)) {
            throw new RuntimeException("assertion failed");
        }
    }

    @DisplayName("hseCarService Sellcar тест с мокнутыми IcustomerProvider, IcarFactory")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    @Test
    public void sellCarMockTest() {
        Customer customer1 = new Customer("Bob1", 10, 10);
        Customer customer2 = new Customer("Bob2", 10, 10);
        Customer customer3 = new Customer("Bob3", 10, 10);

        Mockito.when(mockCustomerProvider.getCustomers()).thenReturn(List.of(customer1, customer2, customer3));

        Car car = new Car(123, new ReviveEngine());
        Mockito.when(mockCarFactoryEmptyEngine.createCar(Mockito.any(EmptyEngineParams.class),
                Mockito.any(Integer.class))).thenReturn(car);

        CarService carProvider = new CarService();
        carProvider.addCar(mockCarFactoryEmptyEngine, EmptyEngineParams.DEFAULT);
        carProvider.addCar(mockCarFactoryEmptyEngine, EmptyEngineParams.DEFAULT);
        carProvider.addCar(mockCarFactoryEmptyEngine, EmptyEngineParams.DEFAULT);

        HseCarService hseCarService1 = new HseCarService(carProvider, mockCustomerProvider);
        hseCarService1.sellCars();

        List<Customer> expected = List.of(customer1, customer2, customer3);
        List<Customer> got = mockCustomerProvider.getCustomers();

        Mockito.verify(mockCustomerProvider, Mockito.times(2)).getCustomers();

        if (!uniqueListsAreEqual(got, expected)) {
            throw new RuntimeException("assertion failed");
        }
    }

    @DisplayName("Sellcar тест с spy IcarProvider ; проверяет что в каждый вызов "
            + "takeCar какая-то Car переходила к Customer")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    @Test
    public void sellCarSpyTest() {
        Customer customer1 = new Customer("Bob1", 10, 10);
        Customer customer2 = new Customer("Bob2", 10, 10);
        Customer customer3 = new Customer("Bob3", 10, 10);
        customerStorage.addCustomer(customer1);
        customerStorage.addCustomer(customer2);
        customerStorage.addCustomer(customer3);


        Car car = new Car(123, new ReviveEngine());
        Mockito.doReturn(car).when(spyCarProvider).takeCar(Mockito.any(Customer.class));

        HseCarService hseCarService1 = new HseCarService(spyCarProvider, customerStorage);
        hseCarService1.sellCars();

        List<Customer> expected = List.of(customer1, customer2, customer3);
        List<Customer> got = customerStorage.getCustomers();

        Mockito.verify(spyCarProvider, Mockito.times(3)).takeCar(Mockito.any());

        if (!uniqueListsAreEqual(got, expected)) {
            throw new RuntimeException("assertion failed");
        }
    }

    /*
    @DisplayName("Попытка подать в завод неправильный тип двигателя - хотим ошибку")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    @Test
    public void wrongEngineIn() {
        Customer customer1 = new Customer("Bob1", 10, 10);
        Customer customer2 = new Customer("Bob2", 10, 10);
        Customer customer3 = new Customer("Bob3", 10, 10);
        customerStorage.addCustomer(customer1);
        customerStorage.addCustomer(customer2);
        customerStorage.addCustomer(customer3);


        Car car = new Car(123, new ReviveEngine());
        Mockito.doReturn(car).when(spyCarProvider).takeCar(Mockito.any(Customer.class));

        HseCarService hseCarService1 = new HseCarService(spyCarProvider, customerStorage);
        hseCarService1.sellCars();

        List<Customer> expected = List.of(customer1, customer2, customer3);
        List<Customer> got = customerStorage.getCustomers();

        Mockito.verify(spyCarProvider, Mockito.times(3)).takeCar(Mockito.any());

        if (!uniqueListsAreEqual(got, expected)) {
            throw new RuntimeException("assertion failed");
        }
    }*/


}
