package hse.kpo.enums;

import java.util.Arrays;
import java.util.Optional;

public enum TrainTypes {
    HAND_TRAIN ("handPower"),
    LEG_TRAIN ("legPower"),
    IQ_TRAIN ("iq");

    private final String type;

    TrainTypes(String type) {
        this.type = type;
    }

    public String toString() {
        return type;
    }

    public static Optional<TrainTypes> get(String typeS) {
        return Arrays.stream(values()).filter(type -> type.type.equals(typeS)).findFirst();
    }
}