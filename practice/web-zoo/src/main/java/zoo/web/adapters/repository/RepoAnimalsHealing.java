package zoo.web.adapters.repository;

import org.springframework.stereotype.Component;
import zoo.web.core.entities.healing.AnimalHealing;
import zoo.web.ishared.IrepoAnimalsHealing;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RepoAnimalsHealing implements IrepoAnimalsHealing {
    List<AnimalHealing> animals = List.of();

    @Override
    public void addAnimalHealing(AnimalHealing animalHealing) {
        animals.add(animalHealing);
    }

    @Override
    public Optional<AnimalHealing> getAnimalHealingById(UUID id) {
        return animals.stream().filter(animal -> animal.getId() == id).findAny();
    }

    @Override
    public void deleteAnimalHealing(AnimalHealing animalHealing) {
        animals.remove(animalHealing);
    }
}
