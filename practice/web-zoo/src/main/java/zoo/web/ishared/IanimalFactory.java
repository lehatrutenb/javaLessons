package zoo.web.ishared;

import zoo.web.core.entities.AllAnimalInfo;
import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.animals.AnimalEatType;
import zoo.web.core.entities.animals.AnimalMale;
import zoo.web.core.entities.animals.AnimalType;
import zoo.web.core.entities.feeding.AnimalFeeding;
import zoo.web.core.entities.feeding.Food;
import zoo.web.core.entities.healing.AnimalHealing;

import java.time.LocalDate;

public interface IanimalFactory {
    public AllAnimalInfo create(AnimalEatType animalEatType,
                                AnimalType animalType,
                                AnimalMale animalMale,
                                Food favoriteFood,
                                LocalDate birthday,
                                boolean isHealthy);

    public AllAnimalInfo create(Animal animal, AnimalFeeding animalFeeding, AnimalHealing animalHealing);
}
