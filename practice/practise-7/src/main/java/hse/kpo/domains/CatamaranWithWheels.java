package hse.kpo.domains;

import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.Engine;
import lombok.Getter;
import lombok.ToString;

/*
adapter for catamaran to use as car
 */
public class CatamaranWithWheels extends Car {
    private final Catamaran catamaran;
    public CatamaranWithWheels(Catamaran catamaran) {
        super(catamaran.getVin() + (int) 1e6, catamaran.getEngine());
        this.catamaran = catamaran;
    }

    @Override
    public boolean isCompatible(Customer customer) {
        return catamaran.getEngine().isCompatible(customer, ProductionTypes.CATAMARAN) &&
                catamaran.getEngine().isCompatible(customer, ProductionTypes.CAR);
    }
}

/*
package hse.kpo.domains;

import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.Engine;
import lombok.Getter;
import lombok.ToString;

/*
@ToString
public class Car {

    private Engine engine;

    @Getter
    private int vin;

    public Car(int vin, Engine engine) {
        this.vin = vin;
        this.engine = engine;
    }

    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer, ProductionTypes.CAR);
    }
}

 */