package zoo.web.core.entities.animals;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
public class Animal {
    @Getter
    private final LocalDate birthday;

    @Getter
    private final AnimalMale animalMale;

    @Getter
    private final UUID id;
}
