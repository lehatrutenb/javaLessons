package zoo.web.core.entities.animals;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

public enum AnimalEatType {
    HERBIVORE ("HERBIVORE"),
    PREDATOR ("PREDATOR"),
    BIRD ("BIRD"),
    WATERLIVING ("WATERLIVING");

    private final String name;

    AnimalEatType(String name) {
        this.name = name;
    }

    public static Optional<AnimalEatType> find(String name) {
        return Arrays.stream(values()).filter(type -> type.name.equals(name)).findFirst();
    }
}
