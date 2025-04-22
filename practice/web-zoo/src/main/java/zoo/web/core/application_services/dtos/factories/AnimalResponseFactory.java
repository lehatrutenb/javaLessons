package zoo.web.core.application_services.dtos.factories;

import org.springframework.stereotype.Component;
import zoo.web.core.application_services.dtos.ifactories.IanimalResponseFactory;
import zoo.web.core.application_services.dtos.response.AnimalResponse;
import zoo.web.core.entities.AllAnimalInfo;
import zoo.web.core.entities.enclosure.Enclosure;

@Component
public class AnimalResponseFactory implements IanimalResponseFactory {

    @Override
    public AnimalResponse create(AllAnimalInfo animalInfo, Enclosure enclosure) {
        return new AnimalResponse(animalInfo.animal().getId().toString(), enclosure.getId().toString());
    }
}
