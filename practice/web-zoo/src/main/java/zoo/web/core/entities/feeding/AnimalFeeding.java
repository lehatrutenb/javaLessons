package zoo.web.core.entities.feeding;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import zoo.web.core.entities.animals.AnimalEatType;
import zoo.web.core.entities.animals.AnimalMale;
import zoo.web.core.entities.animals.AnimalType;

import java.util.UUID;

@RequiredArgsConstructor
public class AnimalFeeding {
    @Getter
    private final AnimalEatType animalEatType;

    @Getter
    private final AnimalType animalType;

    @Getter
    private final AnimalMale animalMale; // amt of food for example

    @Getter
    private final Food favoriteFood;

    @Getter
    private final UUID id;

    public void feed(Food food) {
    }
}
