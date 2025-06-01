package zoo.web.ishared;

import zoo.web.core.entities.animals.AnimalEatType;
import zoo.web.core.entities.enclosure.Enclosure;

public interface IenclosureFactory {
    public Enclosure create(AnimalEatType animalEatType,
                            int size,
                            int maxAnimalAmount);
}
