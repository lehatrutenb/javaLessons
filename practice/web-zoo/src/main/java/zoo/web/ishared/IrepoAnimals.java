package zoo.web.ishared;

import zoo.web.core.entities.AllAnimalInfo;
import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.feeding.AnimalFeeding;
import zoo.web.core.entities.healing.AnimalHealing;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IrepoAnimals {
    public void addAnimal(Animal animal);
    public Optional<Animal> getAnimalById(UUID id);
    public void deleteAnimal(Animal animal);
    public List<Animal> getAnimals();
}