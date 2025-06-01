package zoo.web.ishared;

import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.feeding.AnimalFeeding;

import java.util.Optional;
import java.util.UUID;

public interface IrepoAnimalsFeeding {
    public Optional<AnimalFeeding> getAnimalFeedingById(UUID id);
    public void addAnimalFeeding(AnimalFeeding animalFeeding);
    public void deleteAnimalFeeding(AnimalFeeding animalFeeding);
}
