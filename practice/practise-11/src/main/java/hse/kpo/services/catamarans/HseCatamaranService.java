package hse.kpo.services.catamarans;

import hse.kpo.domains.Catamaran;
import hse.kpo.domains.Customer;
import hse.kpo.domains.cars.Car;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.CustomerProvider;
import hse.kpo.interfaces.cars.CarFactory;
import hse.kpo.interfaces.catamarans.CatamaranFactory;
import hse.kpo.interfaces.catamarans.CatamaranProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import hse.kpo.observers.Sales;
import hse.kpo.observers.SalesObserver;
import hse.kpo.repository.CarRepository;
import hse.kpo.repository.CatamaranRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Сервис продажи катамаранов.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class HseCatamaranService implements CatamaranProvider {

    private final List<SalesObserver> observers = new ArrayList<>();

    private final CatamaranRepository catamaranRepository;

    private final CustomerProvider customerProvider;

    public void addObserver(SalesObserver observer) {
        observers.add(observer);
    }

    private void notifyObserversForSale(Customer customer, ProductionTypes productType, int vin) {
        observers.forEach(obs -> obs.onSale(customer, productType, vin));
    }

    /**
     * Метод продажи катамаранов
     */
    @Sales
    public void sellCatamarans() {
        var customers = customerProvider.getCustomers();
        customers.stream().filter(customer -> Objects.isNull(customer.getCar()))
                .forEach(customer -> {
                    var catamaran = takeCatamaran(customer);
                    if (Objects.nonNull(catamaran)) {
                        customer.setCatamaran(catamaran);
                        notifyObserversForSale(customer, ProductionTypes.CAR, catamaran.getVin());
                    } else {
                        log.warn("No car in CarService");
                    }
                });
    }

    @Override
    public Catamaran takeCatamaran(Customer customer) {

        var filteredCars = catamaranRepository.findAll().stream().filter(catamaran -> catamaran.isCompatible(customer)).toList();

        var firstCatamaran = filteredCars.stream().findFirst();

        firstCatamaran.ifPresent(catamaranRepository::delete);

        return firstCatamaran.orElse(null);
    }

    /**
     * Метод добавления {@link Catamaran} в систему.
     *
     * @param catamaranFactory фабрика для создания катамаранов
     * @param catamaranParams параметры для создания катармарана
     */
    public <T> Catamaran addCatamaran(CatamaranFactory<T> catamaranFactory, T catamaranParams) {
        return catamaranRepository.save(catamaranFactory.create(catamaranParams));
    }

    public Catamaran addExistingCatamaran(Catamaran catamaran) {
        return catamaranRepository.save(catamaran);
    }

    public Optional<Catamaran> findByVin(Integer vin) {
        return catamaranRepository.findById(vin);
    }

    public void deleteByVin(Integer vin) {
        catamaranRepository.deleteById(vin);
    }

    public List<Catamaran> getCarsWithFiltration(String engineType, Integer vin) {
        return catamaranRepository.findCatamaransByEngineTypeAndVinGreaterThan(engineType, vin);
    }

    public List<Catamaran> getCatamarans() {
        return catamaranRepository.findAll();
    }
}