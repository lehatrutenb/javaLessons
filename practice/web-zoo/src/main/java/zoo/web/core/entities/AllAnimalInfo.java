package zoo.web.core.entities;

import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.feeding.AnimalFeeding;
import zoo.web.core.entities.healing.AnimalHealing;

public record AllAnimalInfo(Animal animal, AnimalFeeding animalFeeding, AnimalHealing animalHealing) {
}
