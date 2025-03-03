package hse.kpo.services;

import hse.kpo.domains.Customer;
import hse.kpo.factories.cars.*;
import hse.kpo.factories.catamarans.HandCatamaranFactory;
import hse.kpo.factories.catamarans.LevitationCatamaranFactory;
import hse.kpo.factories.catamarans.PedalCatamaranFactory;
import hse.kpo.observers.ReportSalesObserver;
import hse.kpo.observers.SalesObserver;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.storages.CarStorage;
import hse.kpo.storages.CatamaranStorage;
import hse.kpo.storages.CustomerStorage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    private final HandCatamaranWithWheelsFactory handCatamaranWithWheelsFactory;
    private final PedalCatamaransWithWheelsFactory pedalCatamaransWithWheelsFactory;
    private final LevitationCatamaranWithWheelsFactory levitationCatamaranWithWheelsFactory;
    //private final SalesObserver salesObserver;

    @PostConstruct
    private void HsePostConstr() {
        hseCarService.AddObserver(reportSalesObserver);
        hseCatamaranService.AddObserver(reportSalesObserver);
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
        carStorage.addCar(handCatamaranWithWheelsFactory, EmptyEngineParams.DEFAULT);
    }
    public void AddPedalCatamaranWithWheels(PedalEngineParams pedalEngineParams) {
        carStorage.addCar(pedalCatamaransWithWheelsFactory, pedalEngineParams);
    }
    public void AddLevitationCatamaranWithWheels() {
        carStorage.addCar(levitationCatamaranWithWheelsFactory, EmptyEngineParams.DEFAULT);
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
