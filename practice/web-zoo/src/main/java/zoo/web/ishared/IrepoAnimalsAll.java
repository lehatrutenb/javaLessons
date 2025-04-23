package zoo.web.ishared;

import zoo.web.core.entities.AllAnimalInfo;
import zoo.web.core.entities.animals.Animal;

import java.util.List;

public interface IrepoAnimalsAll extends IrepoAnimals, IrepoAnimalsFeeding, IrepoAnimalsHealing {
    public List<AllAnimalInfo> getAllAnimalsInfo();
}
