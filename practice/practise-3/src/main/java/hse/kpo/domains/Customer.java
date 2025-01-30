package hse.kpo.domains;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Customer {
    private final String name;

    public int iq;
    public int legPower;
    public int handPower;

    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer(String name, int legPower, int handPower) {
        this.name = name;
        this.legPower = legPower;
        this.handPower = handPower;
        this.iq = 0;
    }
    public Customer(String name, int legPower, int handPower, int iq) {
        this.name = name;
        this.legPower = legPower;
        this.handPower = handPower;
        this.iq = iq;
    }
}
