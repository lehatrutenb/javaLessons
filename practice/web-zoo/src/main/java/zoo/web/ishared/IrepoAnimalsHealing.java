package zoo.web.ishared;

import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.healing.AnimalHealing;

import java.util.Optional;
import java.util.UUID;

public interface IrepoAnimalsHealing {
    public void addAnimalHealing(AnimalHealing animalHealing);
    public Optional<AnimalHealing> getAnimalHealingById(UUID id);
    public void deleteAnimalHealing(AnimalHealing animalHealing);
}
