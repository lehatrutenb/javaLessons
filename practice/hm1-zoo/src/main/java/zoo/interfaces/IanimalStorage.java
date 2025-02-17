package zoo.interfaces;

import zoo.domains.animals.Animal;

import java.util.ArrayList;
import java.util.List;

public interface IanimalStorage {
    public void AddAnimal(Animal animal);
    public List<Animal> GetAnimals();
}
