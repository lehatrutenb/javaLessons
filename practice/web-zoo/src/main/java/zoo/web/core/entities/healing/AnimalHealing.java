package zoo.web.core.entities.healing;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import zoo.web.core.entities.animals.AnimalMale;

import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
public class AnimalHealing {
    @Getter
    private final UUID id;

    @Getter
    @NonNull
    private boolean isHealthy;

    @Getter
    private final LocalDate birthday;

    @Getter
    private final AnimalMale animalMale;

    public void heal() {
        isHealthy = true;
    }
}
