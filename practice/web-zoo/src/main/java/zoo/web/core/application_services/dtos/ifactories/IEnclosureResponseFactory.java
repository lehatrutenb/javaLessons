package zoo.web.core.application_services.dtos.ifactories;

import zoo.web.core.application_services.dtos.response.EnclosureResponse;
import zoo.web.core.entities.enclosure.Enclosure;

public interface IEnclosureResponseFactory {
    public EnclosureResponse create(Enclosure enclosure);
}
