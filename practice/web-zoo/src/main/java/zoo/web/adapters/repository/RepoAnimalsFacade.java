package zoo.web.adapters.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zoo.web.core.entities.AllAnimalInfo;
import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.feeding.AnimalFeeding;
import zoo.web.core.entities.healing.AnimalHealing;
import zoo.web.ishared.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RepoAnimalsFacade implements IrepoAnimalsAll, IrepoAnimals, IrepoAnimalsFeeding, IrepoAnimalsHealing {
    private final IrepoAnimals animals;
    private final IrepoAnimalsHealing animalsHealing;
    private final IrepoAnimalsFeeding animalsFeeding;
    private final IanimalFactory animalFactory;

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
    public List<Animal> getAnimals() {
        return animals.getAnimals();
    }

    @Override
    public List<AllAnimalInfo> getAllAnimalsInfo() {
        return animals.getAnimals().stream().map(
                animal -> {
                    AnimalFeeding animalFeeding = animalsFeeding.getAnimalFeedingById(animal.getId()).orElseThrow();
                    AnimalHealing animalHealing = animalsHealing.getAnimalHealingById(animal.getId()).orElseThrow();
                    return animalFactory.create(animal, animalFeeding, animalHealing);
                }
        ).toList();
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
