package hse.kpo.factories;

import hse.kpo.domains.PedalEngine;
import hse.kpo.domains.Ship;
import hse.kpo.interfaces.IshipFactory;
import hse.kpo.params.PedalEngineParams;
import org.springframework.stereotype.Component;

/**
 * Класс фабрики создающей корабли с PedalEngine.
 */
@Component
public class PedalShipFactory implements IshipFactory<PedalEngineParams> {
    @Override
    public Ship createShip(PedalEngineParams shipParams, int shipNumber) {
        var engine = new PedalEngine(shipParams.pedalSize()); // создаем двигатель на основе переданных параметров

        return new Ship(shipNumber, engine); // возвращаем собранный автомобиль с заданным двигателем и данным номером
    }
}
