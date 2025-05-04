package hse.kpo.enums;

import java.util.Arrays;
import java.util.Optional;

public enum TrainTypes {
    HAND_TRAIN ("HAND_TRAIN"),
    LEG_TRAIN ("LEG_TRAIN"),
    IQ_TRAIN ("IQ_TRAIN");

    private final String type;

    TrainTypes(String type) {
        this.type = type;
    }

    public static Optional<TrainTypes> get(String typeS) {
        return Arrays.stream(values()).filter(type -> type.type.equals(typeS)).findFirst();
    }
}