package hse.kpo.domains;

import java.util.*;

import hse.kpo.enums.TrainTypes;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

/**
 * Класс, описывающий покупателя.
 */
@Getter
@Setter
@Entity
@Table(name = "customers")
@NoArgsConstructor
public class Customer {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO) // not GenerationType.IDENTITY cause want to have same ids as in main service
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    // it should be rmed ; You may ask - why? if have it here , so logic of how user is trained during trains is copied in both services - bad
    @Column(nullable = false)
    private int legPower;

    @Column(nullable = false)
    private int handPower;

    @Column(nullable = false)
    private int iq;

/*    public Customer(String name, int legPower, int handPower, int iq) { customer can't be init without id
        this.name = name;
        this.legPower = legPower;
        this.handPower = handPower;
        this.iq = iq;
    }
 */

    public Customer(Integer id, String name, int legPower, int handPower, int iq) {
        this.id = id;
        this.name = name;
        this.legPower = legPower;
        this.handPower = handPower;
        this.iq = iq;
    }

    public Customer train(TrainTypes train) {
        switch (train) {
            case IQ_TRAIN -> {
                iq++;
                return this;
            }
            case LEG_TRAIN -> {
                legPower++;
                return this;
            }
            case HAND_TRAIN -> {
                handPower++;
                return this;
            }
            default -> throw new IllegalArgumentException();
        }
    }

    public String toString() {
        return String.format("id: %s, iq: %s, name: %s, leg power: %s, hand power: %s", id.toString(), iq, name, legPower, handPower);
    }
}
