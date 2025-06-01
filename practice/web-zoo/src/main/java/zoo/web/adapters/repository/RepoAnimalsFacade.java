package zoo.web.adapters.repository;

import org.springframework.stereotype.Component;
import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.feeding.AnimalFeeding;
import zoo.web.core.entities.healing.AnimalHealing;
import zoo.web.ishared.IrepoAnimals;
import zoo.web.ishared.IrepoAnimalsAll;
import zoo.web.ishared.IrepoAnimalsFeeding;
import zoo.web.ishared.IrepoAnimalsHealing;

import java.util.Optional;
import java.util.UUID;

@Component
public class RepoAnimalsFacade implements IrepoAnimalsAll, IrepoAnimals, IrepoAnimalsFeeding, IrepoAnimalsHealing {
    IrepoAnimals animals;
    IrepoAnimalsHealing animalsHealing;
    IrepoAnimalsFeeding animalsFeeding;

    @Override
    public void addAnimal(Animal animal) {
        animals.addAnimal(animal);
    }

    @Override
    public Optional<Animal> getAnimalById(UUID id) {
        return animals.getAnimalById(id);
    }

    @Override
    public void deleteAnimal(Animal animal) {
        animals.deleteAnimal(animal);
    }

    @Override
    public Optional<AnimalFeeding> getAnimalFeedingById(UUID id) {
        return animalsFeeding.getAnimalFeedingById(id);
    }

    @Override
    public void addAnimalFeeding(AnimalFeeding animalFeeding) {
        animalsFeeding.addAnimalFeeding(animalFeeding);
    }

    @Override
    public void deleteAnimalFeeding(AnimalFeeding animalFeeding) {
        animalsFeeding.deleteAnimalFeeding(animalFeeding);
    }

    @Override
    public void addAnimalHealing(AnimalHealing animalHealing) {
        animalsHealing.addAnimalHealing(animalHealing);
    }

    @Override
    public Optional<AnimalHealing> getAnimalHealingById(UUID id) {
        return animalsHealing.getAnimalHealingById(id);
    }

    @Override
    public void deleteAnimalHealing(AnimalHealing animalHealing) {
        animalsHealing.deleteAnimalHealing(animalHealing);
    }
}
