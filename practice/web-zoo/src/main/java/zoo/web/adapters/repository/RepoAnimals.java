package zoo.web.adapters.repository;

import org.springframework.stereotype.Component;
import zoo.web.core.entities.animals.Animal;
import zoo.web.ishared.IrepoAnimals;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RepoAnimals implements IrepoAnimals {
    List<Animal> animals = List.of();

    @Override
    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    @Override
    public Optional<Animal> getAnimalById(UUID id) {
        return animals.stream().filter(animal -> animal.getId() == id).findAny();
    }

    @Override
    public void deleteAnimal(Animal animal) {
        animals.remove(animal);
    }
}
