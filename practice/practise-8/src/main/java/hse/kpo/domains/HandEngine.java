package hse.kpo.domains;

import hse.kpo.domains.customers.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.Engine;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс, реализующий {@link Engine} ручного типа.
 */
@ToString
@XmlRootElement(name="Engine")
public class HandEngine implements Engine {
    @Override
    public boolean isCompatible(Customer customer, ProductionTypes type) {
        return switch (type) {
            case ProductionTypes.CAR -> customer.getHandPower() > 5;
            case ProductionTypes.CATAMARAN -> customer.getHandPower() > 2;
            case null, default -> throw new RuntimeException("This type of production doesn't exist");
        };
    }
}
