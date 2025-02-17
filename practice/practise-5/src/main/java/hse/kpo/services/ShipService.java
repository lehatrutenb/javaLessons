package hse.kpo.services;


import java.util.ArrayList;
import java.util.List;

import hse.kpo.domains.Customer;
import hse.kpo.domains.Ship;
import hse.kpo.interfaces.IshipFactory;
import hse.kpo.interfaces.IshipProvider;
import org.springframework.stereotype.Component;

/**
 * Класс для хранению имеющихся Car.
 */
@Component
public class ShipService implements IshipProvider {

    private final List<Ship> ships = new ArrayList<>();

    private int shipNumberCounter = 0;

    @Override
    public Ship takeShip(Customer customer) {

        var filteredShips = ships.stream().filter(ship -> ship.isCompatible(customer)).toList();

        var firstCar = filteredShips.stream().findFirst();

        firstCar.ifPresent(ships::remove);

        return firstCar.orElse(null);
    }

    /**
     * Метод для регистрации новой машины с данной фабрики и данными параметрами.
     */
    public <SparamsT> void addShip(IshipFactory<SparamsT> shipFactory, SparamsT shipParams) {
        // создаем автомобиль из переданной фабрики
        var ship = shipFactory.createShip(
                shipParams, // передаем параметры
                ++shipNumberCounter // передаем номер - номер будет начинаться с 1
        );

        ships.add(ship); // добавляем автомобиль
    }
}
