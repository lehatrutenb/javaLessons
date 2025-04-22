package zoo.web.core.application_services.dtos.factories;

import org.springframework.stereotype.Component;
import zoo.web.core.application_services.dtos.ifactories.IEnclosureResponseFactory;
import zoo.web.core.application_services.dtos.response.EnclosureResponse;
import zoo.web.core.entities.enclosure.Enclosure;

@Component
public class EnclosureResponseFactory implements IEnclosureResponseFactory {
    @Override
    public EnclosureResponse create(Enclosure enclosure) {
        return new EnclosureResponse(enclosure.getId().toString());
    }
}
