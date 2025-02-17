package hse.kpo.domains;

import lombok.Getter;
import lombok.ToString;

/**
 * Класс покупателя машины.
 */
@Getter
@ToString
public class Customer {
    private final String name;

    public int iq;
    public int legPower;
    public int handPower;

    private Car car;
    private Ship ship;

    public Car getCar() {
        return car;
    }
    public Ship getShip() {
        return ship;
    }

    public void setCar(Car car) {
        this.car = car;
    }
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * Создание пользователя без задания iq.
     */
    public Customer(String name, int legPower, int handPower) {
        this.name = name;
        this.legPower = legPower;
        this.handPower = handPower;
        this.iq = 0;
    }

    /**
     * Создание пользователя с заданием iq.
     */
    public Customer(String name, int legPower, int handPower, int iq) {
        this.name = name;
        this.legPower = legPower;
        this.handPower = handPower;
        this.iq = iq;
    }
}
