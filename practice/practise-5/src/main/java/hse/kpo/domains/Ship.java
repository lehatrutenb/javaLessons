package hse.kpo.domains;

import hse.kpo.interfaces.Iengine;
import lombok.Getter;
import lombok.ToString;

/**
 * Класс машины.
 */
@ToString
public class Ship {

    private Iengine engine;

    @Getter
    private int vin;

    public Ship(int vin, Iengine engine) {
        this.vin = vin;
        this.engine = engine;
    }

    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer); // внутри метода просто вызываем соответствующий метод двигателя
    }
}
