package studying.domains;

import lombok.ToString;
import studying.domains.Customer;
import studying.interfaces.IEngine;

@ToString
public class ReviveEngine implements IEngine {
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.iq > 300;
    }
}
