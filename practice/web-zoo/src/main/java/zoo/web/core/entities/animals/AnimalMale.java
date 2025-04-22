package zoo.web.core.entities.animals;

import java.util.Arrays;
import java.util.Optional;

public enum AnimalMale {
    MALE("MALE"),
    FEMALE("FEMALE"),
    UNIFY("UNIFY");

    private final String name;

    AnimalMale(String name) {
        this.name = name;
    }

    public static Optional<AnimalMale> find(String name) {
        return Arrays.stream(values()).filter(type -> type.name.equals(name)).findFirst();
    }
}
