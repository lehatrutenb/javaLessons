package hse.kpo.factories;

import hse.kpo.domains.HandEngine;
import hse.kpo.domains.Ship;
import hse.kpo.interfaces.IshipFactory;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

/**
 * Класс фабрики создающей корабля с HandEngine.
 */
@Component
public class HandShipFactory implements IshipFactory<EmptyEngineParams> {
    @Override
    public Ship createShip(EmptyEngineParams shipParams, int shipNumber) {
        var engine = new HandEngine(); // Создаем двигатель без каких-либо параметров

        return new Ship(shipNumber, engine); // создаем автомобиль с ручным приводом
    }
}
