package hse.kpo.domains.cars;

import hse.kpo.domains.customers.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.Engine;
import hse.kpo.interfaces.Transport;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Класс хранящий информацию о машине.
 */
@ToString
public class Car implements Transport {
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

    public String getEngineType() {
        return engine.toString();
    }

    public String getTransportType() {
        return "car";
    }
}
