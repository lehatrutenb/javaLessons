package hse.kpo;

import hse.kpo.builders.ReportBuilder;
import hse.kpo.domains.Customer;
import hse.kpo.factories.cars.HandCarFactory;
import hse.kpo.factories.cars.PedalCarFactory;
import hse.kpo.factories.catamarans.HandCatamaranFactory;
import hse.kpo.factories.catamarans.PedalCatamaranFactory;
import hse.kpo.observers.ReportSalesObserver;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.Hse;
import hse.kpo.services.HseCatamaranService;
import hse.kpo.storages.CarStorage;
import hse.kpo.storages.CatamaranStorage;
import hse.kpo.storages.CustomerStorage;
import hse.kpo.services.HseCarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KpoApplicationTests {

	@Autowired
	ReportSalesObserver reportSalesObserver;

	@Autowired
	Hse hse;

	@Test
	@DisplayName("Тест загрузки контекста машин")
	void hseCarServiceTest() {
		hse.AddCustomer(Customer.builder().name("Ivan1").legPower(6).handPower(4).build());
		hse.AddCustomer(Customer.builder().name("Maksim").legPower(4).handPower(6).build());
		hse.AddCustomer(Customer.builder().name("Petya").legPower(6).handPower(6).build());
		hse.AddCustomer(Customer.builder().name("Nikita").legPower(4).handPower(4).build());

		hse.AddPedalCar(new PedalEngineParams(6));
		hse.AddPedalCar(new PedalEngineParams(6));
		hse.AddPedalCatamaranWithWheels(new PedalEngineParams(6));

		hse.AddHandCar();
		hse.AddHandCar();

		hse.GetCustomers().stream().map(Customer::toString).forEach(System.out::println);

		hse.AddObserver(reportSalesObserver);

		hse.SellCars();

		hse.GetCustomers().stream().map(Customer::toString).forEach(System.out::println);

		System.out.println();
		System.out.println(reportSalesObserver.buildReport());
	}

	@Test
	@DisplayName("Тест загрузки контекста катамаранов")
	void hseCatamaranServiceTest() {
		hse.AddCustomer(Customer.builder().name("Ivan1").legPower(6).handPower(4).build());
		hse.AddCustomer(Customer.builder().name("Maksim").legPower(4).handPower(6).build());
		hse.AddCustomer(Customer.builder().name("Petya").legPower(6).handPower(6).build());
		hse.AddCustomer(Customer.builder().name("Nikita").legPower(4).handPower(4).build());

		hse.AddPedalCatamaran(new PedalEngineParams(6));
		hse.AddPedalCatamaran(new PedalEngineParams(6));

		hse.AddHandCatamaran();
		hse.AddHandCatamaran();

		hse.GetCustomers().stream().map(Customer::toString).forEach(System.out::println);

		hse.AddObserver(reportSalesObserver);

		hse.SellCatamarans();

		hse.GetCustomers().stream().map(Customer::toString).forEach(System.out::println);

		System.out.println();
		System.out.println(reportSalesObserver.buildReport());
	}

}
