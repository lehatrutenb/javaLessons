package zoo.services;

import org.springframework.stereotype.Component;
import zoo.domains.animals.Animal;
import zoo.interfaces.IanimalStorage;
import zoo.interfaces.IthingStorage;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnimalStorage implements IanimalStorage {
    private List<Animal> animals = new ArrayList<>();
    public void AddAnimal(Animal animal) {
        animals.add(animal);
    }

    public List<Animal> GetAnimals() {
        return animals;
    }
}
