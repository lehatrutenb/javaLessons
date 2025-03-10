package hse.kpo.domains;

import hse.kpo.domains.cars.Car;
import hse.kpo.domains.catamarans.Catamaran;
import hse.kpo.domains.customers.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.export.EngineTypeS;
import hse.kpo.interfaces.Engine;
import hse.kpo.interfaces.Transport;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
<Vehicle>
    <VIN>4</VIN>
    <Type>car</Type>
    <Engine>
        <Type>HandEngine()</Type>
    </Engine>
</Vehicle>
 */

@XmlRootElement(name="Vehicle")
public class PureTransport implements Transport {
    @XmlElement(name="Engine")
    private EngineTypeS engineS;

    //@Getter
    //@Setter
    @XmlElement(name="VIN")
    private int vin;

    @XmlElement(name="Type")
    private String transportTypeS;

    private ProductionTypes getProductionType() {
        switch (transportTypeS) {
            case "car" -> {return ProductionTypes.CAR;}
            case "catamaran" -> {return ProductionTypes.CATAMARAN;}
        }
        throw new IllegalArgumentException("Unsupported prod type: " + transportTypeS);
    }

    PureTransport(String engineS, int vin, )

    @Override
    public boolean isCompatible(Customer customer) {
        return engineS.getEngine().isCompatible(customer, getProductionType());
    }


    @Override
    public int getVin() {
        return vin;
    }

    @Override
    public String getEngineType() {
        return engineS.getType();
    }

    public void setTransportTypeS(String transportTypeS) {
        this.transportTypeS = transportTypeS;
    }

    @Override
    public String getTransportType() {
        return transportTypeS;
    }

    public Car toCar() {
        return new Car(vin, engineS.getEngine());
    }

    public Catamaran toCatamaran() {
        return new Catamaran(vin, engineS.getEngine());
    }
}
