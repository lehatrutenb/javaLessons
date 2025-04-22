package zoo.web.ishared;

import zoo.web.core.entities.animals.AnimalType;

public interface IanimalTypeFactory {
    public AnimalType create(String type);
}
