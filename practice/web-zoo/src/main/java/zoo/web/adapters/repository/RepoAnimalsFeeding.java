package zoo.web.adapters.repository;

import org.springframework.stereotype.Component;
import zoo.web.core.entities.feeding.AnimalFeeding;
import zoo.web.ishared.IrepoAnimalsFeeding;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RepoAnimalsFeeding implements IrepoAnimalsFeeding {
    List<AnimalFeeding> animals = new ArrayList<>();

    @Override
    public void addAnimalFeeding(AnimalFeeding animalFeeding) {
        animals.add(animalFeeding);
    }

    @Override
    public void deleteAnimalFeeding(AnimalFeeding animalFeeding) {
        animals.remove(animalFeeding);
    }

    @Override
    public Optional<AnimalFeeding> getAnimalFeedingById(UUID id) {
        return animals.stream().filter(animal -> animal.getId().equals(id)).findAny();
    }
}
