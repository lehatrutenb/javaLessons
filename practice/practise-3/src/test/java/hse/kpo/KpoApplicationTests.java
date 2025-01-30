package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.factories.ReviveCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.CarService;
import hse.kpo.services.CustomerStorage;
import hse.kpo.services.HseCarService;
import hse.kpo.tests.TestStr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class KpoApplicationTests {
	@Autowired
	CarService carService;
	@Autowired
	CustomerStorage customerStorage;
	@Autowired
	HseCarService hseCarService;
	@Autowired
	HandCarFactory handCarFactory;
	@Autowired
	PedalCarFactory pedalCarFactory;
	@Autowired
	ReviveCarFactory reviveCarFactory;

	private static <T> boolean UniqueListsAreEqual(List<T> list1, List<T> list2) {
		return list1.containsAll(list2) && list2.containsAll(list1);
	}

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

		if (!UniqueListsAreEqual(got, expected)) {
			throw new RuntimeException("assertion failed");
		}
	}

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

		if (!UniqueListsAreEqual(got, expected)) {
			throw new RuntimeException("assertion failed");
		}
	}

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

		if (!UniqueListsAreEqual(got, expected)) {
			throw new RuntimeException("assertion failed");
		}
		//teardown();
	}


	/*public static void main(String[] args) {
		TestStr testStr = new TestStr();

		defaultTest(testStr);
		testReviveCar(testStr);
		testReviveAndOthers(testStr);
		System.out.println("succeed tests");
	}*/

	/*@Test
	@DisplayName("Тест загрузки контекста")
	void contextLoads() {
	}*/

}
