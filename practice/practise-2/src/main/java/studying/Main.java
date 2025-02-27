package studying;

import studying.domains.Customer;
import studying.factories.HandCarFactory;
import studying.factories.PedalCarFactory;
import studying.params.EmptyEngineParams;
import studying.params.PedalEngineParams;
import studying.services.CarService;
import studying.services.CustomerStorage;
import studying.services.HseCarService;
import studying.tests.TestStr;

import java.util.List;

public class Main {
    private static <T> boolean UniqueListsAreEqual(List<T> list1, List<T> list2) {
        return list1.containsAll(list2) && list2.containsAll(list1);
    }

    private static void testReviveCar(TestStr testStr) {
        testStr.setup();

        testStr.carService.addCar(testStr.reviveCarFactory, EmptyEngineParams.DEFAULT);
        testStr.carService.addCar(testStr.reviveCarFactory, EmptyEngineParams.DEFAULT);

        Customer customer1 = new Customer("1", 0, 0, 300);
        Customer customer2 = new Customer("1", 0, 0, 301);
        Customer customer3 = new Customer("1", 0, 0, -301);
        testStr.customerStorage.addCustomer(customer1);
        testStr.customerStorage.addCustomer(customer2);
        testStr.customerStorage.addCustomer(customer3);

        testStr.hseCarService.sellCars();

        List<Customer> expected = Arrays.asList(customer1, customer3);
        List<Customer> got = testStr.customerStorage.getCustomers().stream().filter(
                customer -> Objects.isNull(customer.getCar())).toList();

        if (!UniqueListsAreEqual(got, expected)) {
            throw new RuntimeException("assertion failed");
        }
        testStr.teardown();
    }

    private static void testReviveAndOthers(TestStr testStr) {
        testStr.setup();

        testStr.carService.addCar(testStr.reviveCarFactory, EmptyEngineParams.DEFAULT);
        testStr.carService.addCar(testStr.reviveCarFactory, EmptyEngineParams.DEFAULT);
        testStr.carService.addCar(testStr.pedalCarFactory, new PedalEngineParams(10));
        testStr.carService.addCar(testStr.handCarFactory, EmptyEngineParams.DEFAULT);

        Customer customer1 = new Customer("1", 0, 0, 301);
        Customer customer2 = new Customer("1", 10, 10, 300);
        Customer customer3 = new Customer("1", 10, 10, -301);
        Customer customer4 = new Customer("1", 0, 0, (int) 2e9);
        testStr.customerStorage.addCustomer(customer1);
        testStr.customerStorage.addCustomer(customer2);
        testStr.customerStorage.addCustomer(customer3);
        testStr.customerStorage.addCustomer(customer4);

        testStr.hseCarService.sellCars();

        List<Customer> expected = Arrays.asList();
        List<Customer> got = testStr.customerStorage.getCustomers().stream().filter(
                customer -> Objects.isNull(customer.getCar())).toList();

        if (!UniqueListsAreEqual(got, expected)) {
            throw new RuntimeException("assertion failed");
        }
        testStr.teardown();
    }

    private static void defaultTest(TestStr testStr) {
        testStr.setup();

        Customer customerNotGetCar = new Customer("Bob4", 4, 4);
        testStr.customerStorage.addCustomer(new Customer("Bob1", 6, 4));
        testStr.customerStorage.addCustomer(new Customer("Bob2", 4, 6));
        testStr.customerStorage.addCustomer(new Customer("Bob3", 6, 6));
        testStr.customerStorage.addCustomer(customerNotGetCar);

        testStr.carService.addCar(testStr.handCarFactory, EmptyEngineParams.DEFAULT);
        testStr.carService.addCar(testStr.handCarFactory, EmptyEngineParams.DEFAULT);

        testStr.carService.addCar(testStr.pedalCarFactory, new PedalEngineParams(10));
        testStr.carService.addCar(testStr.pedalCarFactory, new PedalEngineParams(10));

        System.out.println(testStr.customerStorage.getCustomers());

        testStr.hseCarService.sellCars();

        System.out.println(testStr.customerStorage.getCustomers()); // got expected output

        List<Customer> expected = Arrays.asList(customerNotGetCar);
        List<Customer> got = testStr.customerStorage.getCustomers().stream().filter(
                customer -> Objects.isNull(customer.getCar())).toList();

        if (!UniqueListsAreEqual(got, expected)) {
            throw new RuntimeException("assertion failed");
        }
        testStr.teardown();
    }

    public static void main(String[] args) {
        TestStr testStr = new TestStr();

        defaultTest(testStr);
        testReviveCar(testStr);
        testReviveAndOthers(testStr);
        System.out.println("succeed tests");
    }
}
