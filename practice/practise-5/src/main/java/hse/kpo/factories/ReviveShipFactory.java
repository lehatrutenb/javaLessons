package hse.kpo.factories;

import hse.kpo.domains.ReviveEngine;
import hse.kpo.domains.Ship;
import hse.kpo.interfaces.IshipFactory;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

/**
 * Класс фабрики создающей машины с ReviveEngine.
 */
@Component
public class ReviveShipFactory implements IshipFactory<EmptyEngineParams> {
    @Override
    public Ship createShip(EmptyEngineParams shipParams, int shipNumber) {
        var engine = new ReviveEngine(); // создаем двигатель на основе переданных параметров

        return new Ship(shipNumber, engine); // возвращаем собранный автомобиль с заданным двигателем и данным номером
    }
}
