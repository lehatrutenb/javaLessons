package hse.kpo.domains;

import hse.kpo.enums.TrainTypes;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

public record Train (Customer customer, TrainTypes trainType) { }
