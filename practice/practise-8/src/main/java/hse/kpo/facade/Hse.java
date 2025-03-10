package hse.kpo.facade;

import hse.kpo.domains.PureTransport;
import hse.kpo.domains.Report;
import hse.kpo.domains.cars.Car;
import hse.kpo.domains.customers.Customer;
import hse.kpo.enums.ReportFormat;
import hse.kpo.enums.TransportFormat;
import hse.kpo.export.reports.ReportExporter;
import hse.kpo.factories.ReportExporterFactory;
import hse.kpo.factories.TransportExporterFactory;
import hse.kpo.factories.TransportImportererFactory;
import hse.kpo.factories.cars.*;
import hse.kpo.factories.catamarans.HandCatamaranFactory;
import hse.kpo.factories.catamarans.LevitationCatamaranFactory;
import hse.kpo.factories.catamarans.PedalCatamaranFactory;
import hse.kpo.interfaces.Transport;
import hse.kpo.interfaces.TransportExporter;
import hse.kpo.interfaces.TransportImporter;
import hse.kpo.observers.ReportSalesObserver;
import hse.kpo.observers.SalesObserver;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.cars.HseCarService;
import hse.kpo.services.catamarans.HseCatamaranService;
import hse.kpo.storages.TransportStorage;
import hse.kpo.storages.cars.CarStorage;
import hse.kpo.storages.catamarans.CatamaranStorage;
import hse.kpo.storages.customers.CustomerStorage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
@Slf4j
@RequiredArgsConstructor
public class Hse {
    private final CarStorage carStorage;
    private final CatamaranStorage catamaranStorage;
    private final CustomerStorage customerStorage;
    private final HseCarService hseCarService;
    private final HseCatamaranService hseCatamaranService;
    private final PedalCarFactory pedalCarFactory;
    private final PedalCatamaranFactory pedalCatamaranFactory;
    private final HandCarFactory handCarFactory;
    private final LevitationCarFactory levitationCarFactory;
    private final HandCatamaranFactory handCatamaranFactory;
    private final LevitationCatamaranFactory levitationCatamaranFactory;
    private final ReportSalesObserver reportSalesObserver;
    private final CatamaranWithWheelsFactory catamaranWithWheelsFactory;
    private final ReportExporterFactory reportExporterFactory;
    private final TransportExporterFactory transportExporterFactory;
    private final TransportImportererFactory transportImportererFactory;
    //private final SalesObserver salesObserver;

    @PostConstruct
    private void HsePostConstr() {
        hseCarService.AddObserver(reportSalesObserver);
        hseCatamaranService.AddObserver(reportSalesObserver);
    }

    public void importTransport(TransportFormat format, Reader reader) throws RuntimeException {
        TransportImporter importer = transportImportererFactory.create(format);

        List<PureTransport> transports = importer.importer(reader);
        transports.stream().filter(transport -> Objects.equals(transport.getTransportType(), "car")).forEach(transport -> carStorage.addExitingCar(transport.toCar()));
        transports.stream().filter(transport -> Objects.equals(transport.getTransportType(), "catamaran")).forEach(transport -> catamaranStorage.addExitingCatamaran(transport.toCatamaran()));
    }

    public void exportTransport(TransportFormat format, Writer writer) throws RuntimeException {
        TransportExporter exporter = transportExporterFactory.create(format);

        List<Transport> transports = Stream.concat(
                        carStorage.getCars().stream(),
                        catamaranStorage.getCatamarans().stream())
                .toList();

        try {
            exporter.export(transports, writer);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void exportReport(ReportFormat format, Writer writer) throws RuntimeException {
        Report report = reportSalesObserver.buildReport();
        ReportExporter exporter = reportExporterFactory.create(format);

        try {
            exporter.export(report, writer);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    // Старый метод для обратной совместимости
    public String generateReport() {
        return reportSalesObserver.buildReport().toString();
    }

    public void SellCars() {
        hseCarService.sellCars();
    }

    public void SellCatamarans() {
        hseCatamaranService.sellCatamarans();
    }

    public void AddObserver(SalesObserver salesObserver) {
        hseCarService.AddObserver(salesObserver);
        hseCatamaranService.AddObserver(salesObserver);
    }

    public void AddCustomer(Customer customer) {
        customerStorage.addCustomer(customer);
    }

    public List<Customer> GetCustomers() {
        return customerStorage.getCustomers();
    }

    public void AddHandCar() {
        carStorage.addCar(handCarFactory, EmptyEngineParams.DEFAULT);
    }
    public void AddPedalCar(PedalEngineParams pedalEngineParams) {
        carStorage.addCar(pedalCarFactory, pedalEngineParams);
    }
    public void AddLevitationCar() {
        carStorage.addCar(levitationCarFactory, EmptyEngineParams.DEFAULT);
    }

    public void AddHandCatamaranWithWheels() {
        carStorage.addExitingCar(catamaranWithWheelsFactory.create(catamaranStorage.createCatamaran(handCatamaranFactory, EmptyEngineParams.DEFAULT)));
    }
    public void AddPedalCatamaranWithWheels(PedalEngineParams pedalEngineParams) {
        carStorage.addExitingCar(catamaranWithWheelsFactory.create(catamaranStorage.createCatamaran(pedalCatamaranFactory, pedalEngineParams)));
    }
    public void AddLevitationCatamaranWithWheels() {
        carStorage.addExitingCar(catamaranWithWheelsFactory.create(catamaranStorage.createCatamaran(levitationCatamaranFactory, EmptyEngineParams.DEFAULT)));
    }

    public void AddHandCatamaran() {
        catamaranStorage.addCatamaran(handCatamaranFactory, EmptyEngineParams.DEFAULT);
    }
    public void AddPedalCatamaran(PedalEngineParams pedalEngineParams) {
        catamaranStorage.addCatamaran(pedalCatamaranFactory, pedalEngineParams);
    }
    public void AddLevitationCatamaran() {
        catamaranStorage.addCatamaran(levitationCatamaranFactory, EmptyEngineParams.DEFAULT);
    }
}
