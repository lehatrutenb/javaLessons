package hse.kpo.storages.catamarans;

import hse.kpo.domains.cars.Car;
import hse.kpo.domains.catamarans.Catamaran;
import hse.kpo.domains.customers.Customer;
import hse.kpo.interfaces.catamarans.CatamaranFactory;
import hse.kpo.interfaces.catamarans.CatamaranProvider;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * Хранилище информации о катамаранах.
 */
@Component
public class CatamaranStorage implements CatamaranProvider {

    @Getter
    private final List<Catamaran> catamarans = new ArrayList<>();

    private int carNumberCounter = 0;

    @Override
    public Catamaran takeCatamaran(Customer customer) {

        var filteredCars = catamarans.stream().filter(car -> car.isCompatible(customer)).toList();

        var firstCar = filteredCars.stream().findFirst();

        firstCar.ifPresent(catamarans::remove);

        return firstCar.orElse(null);
    }

    /**
     * Метод добавления {@link Catamaran} в систему.
     *
     * @param catamaranFactory фабрика для создания катамаранов
     * @param catamaranParams параметры для создания катамарана
     */
    public <ProductionParams> void addCatamaran(CatamaranFactory<ProductionParams> catamaranFactory,
                                                ProductionParams catamaranParams) {
        var car = catamaranFactory.create(
                catamaranParams,
                ++carNumberCounter
        );

        catamarans.add(car);
    }

    /**
     * Метод создания без добавления {@link Catamaran} в систему.
     *
     * @param catamaranFactory фабрика для создания катамаранов
     * @param catamaranParams параметры для создания катамарана
     */
    public <ProductionParams> Catamaran createCatamaran(CatamaranFactory<ProductionParams> catamaranFactory,
                                                ProductionParams catamaranParams) {
        var catamaran = catamaranFactory.create(
                catamaranParams,
                ++carNumberCounter
        );

        return catamaran;
    }

    public void addExitingCatamaran(Catamaran catamaran) {
        catamarans.add(catamaran);
    }
}
