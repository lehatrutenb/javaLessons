package hse.kpo;

import hse.kpo.domains.customers.Customer;
import hse.kpo.enums.ReportFormat;
import hse.kpo.enums.TransportFormat;
import hse.kpo.observers.ReportSalesObserver;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.facade.Hse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

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

		// Экспорт в консоль в формате Markdown
		hse.exportReport(ReportFormat.MARKDOWN, new PrintWriter(System.out));
		hse.exportReport(ReportFormat.JSON, new PrintWriter(System.out));
		// Экспорт в файл в формате MARKDOWN
		try (FileWriter fileWriter = new FileWriter("report.MD")) {
			hse.exportReport(ReportFormat.MARKDOWN, fileWriter);
		} catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Экспорт в файл в формате JSON
		try (FileWriter fileWriter = new FileWriter("report.json")) {
			hse.exportReport(ReportFormat.JSON, fileWriter);
		} catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
		System.out.println(hse.generateReport());

		hse.exportTransport(TransportFormat.CSV, new PrintWriter(System.out));
		hse.exportTransport(TransportFormat.XML, new PrintWriter(System.out));

		try (FileWriter fileWriter = new FileWriter("transport.xml")) {
			hse.exportTransport(TransportFormat.XML, fileWriter);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		try (FileReader fileReader = new FileReader("transport.xml")) {
			hse.importTransport(TransportFormat.XML, fileReader);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		hse.exportTransport(TransportFormat.XML, new PrintWriter(System.out));
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
