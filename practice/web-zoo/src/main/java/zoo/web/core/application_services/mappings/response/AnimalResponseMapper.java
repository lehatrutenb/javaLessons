package zoo.web.core.application_services.mappings.response;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zoo.web.core.application_services.dtos.ifactories.IanimalResponseFactory;
import zoo.web.core.application_services.dtos.request.AnimalRequest;
import zoo.web.core.application_services.dtos.request.EnclosureRequest;
import zoo.web.core.application_services.dtos.response.AnimalResponse;
import zoo.web.core.entities.AllAnimalInfo;
import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.animals.AnimalEatType;
import zoo.web.core.entities.animals.AnimalMale;
import zoo.web.core.entities.enclosure.Enclosure;
import zoo.web.core.entities.feeding.AnimalFeeding;
import zoo.web.core.entities.healing.AnimalHealing;
import zoo.web.ishared.*;

        import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class AnimalResponseMapper {
    private final IanimalResponseFactory animalResponseFactory;
    public AnimalResponse getAnimalResponse(AllAnimalInfo animalInfo, Enclosure enclosure) {
        return animalResponseFactory.create(animalInfo, enclosure);
    }
}
