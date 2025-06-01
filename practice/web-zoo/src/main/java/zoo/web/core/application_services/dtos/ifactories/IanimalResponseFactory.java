package zoo.web.core.application_services.dtos.ifactories;

import zoo.web.core.application_services.dtos.response.AnimalResponse;
import zoo.web.core.entities.AllAnimalInfo;
import zoo.web.core.entities.enclosure.Enclosure;

public interface IanimalResponseFactory {
    public AnimalResponse create(AllAnimalInfo animalInfo, Enclosure enclosure);
}
