package zoo.web.core.application_services.mappings.response;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zoo.web.core.application_services.dtos.ifactories.IEnclosureResponseFactory;
import zoo.web.core.application_services.dtos.ifactories.IanimalResponseFactory;
import zoo.web.core.application_services.dtos.response.AnimalResponse;
import zoo.web.core.application_services.dtos.response.EnclosureResponse;
import zoo.web.core.entities.AllAnimalInfo;
import zoo.web.core.entities.enclosure.Enclosure;

@RequiredArgsConstructor
@Component
public class EnclosureResponseMapper {
    private final IEnclosureResponseFactory enclosureResponseFactory;
    public EnclosureResponse getEnclosureResponse(Enclosure enclosure) {
        return enclosureResponseFactory.create(enclosure);
    }
}