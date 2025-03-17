package hse.kpo.enums;

import java.util.Optional;

public enum EngineTypes {
    HAND,
    PEDAL,
    LEVITATION;

    public static Optional<EngineTypes> find(String typeName) {
        switch (typeName) {
            case "HAND"->{return Optional.of(HAND);}
            case "PEDAL"->{return Optional.of(PEDAL);}
            case "LEVITATION"->{return Optional.of(LEVITATION);}
        }
        throw new IllegalArgumentException("Unsupported engine type: " + typeName);
    }
}
