package hse.kpo.domains.catamarans;

import hse.kpo.domains.customers.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.Engine;
import hse.kpo.interfaces.Transport;
import lombok.Getter;
import lombok.ToString;

/**
 * Класс хранящий информацию о катамаране.
 */
@ToString
public class Catamaran implements Transport {

    @Getter
    private Engine engine;

    @Getter
    private int vin;

    public Catamaran(int vin, Engine engine) {
        this.vin = vin;
        this.engine = engine;
    }

    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer, ProductionTypes.CATAMARAN);
    }

    public String getEngineType() {
        return engine.toString();
    }

    public String getTransportType() {
        return "catamaran";
    }
}
