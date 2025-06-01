package zoo.web.core.entities.enclosure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.animals.AnimalEatType;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class Enclosure {
    @Getter
    private final UUID id;

    @Getter
    private final AnimalEatType animalEatType;

    @Getter
    private final int size;

    public int getCurAnimalAmount() {
        return animals.size();
    }

    @Getter
    private final int maxAnimalAmount;

    private List<Animal> animals = List.of();

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public boolean hasAnimal(Animal animal) {
        return !animals.stream().filter(curAnimal -> curAnimal.getId() == animal.getId()).toList().isEmpty();
    }

    public boolean removeAnimal(Animal animal) {
        return animals.remove(animal);
    }
}
