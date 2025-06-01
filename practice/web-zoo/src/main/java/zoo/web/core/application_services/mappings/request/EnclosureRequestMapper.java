package zoo.web.core.application_services.mappings.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zoo.web.core.application_services.dtos.request.AnimalRequest;
import zoo.web.core.application_services.dtos.request.EnclosureRequest;
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
public class EnclosureRequestMapper {
    private final IenclosureFactory enclosureFactory;
    private final IrepoEnclosures repoEnclosures;

    public Enclosure getEnclosure(EnclosureRequest enclosureRequest) {
        return enclosureFactory.create(AnimalEatType.find(
                enclosureRequest.animalEatType()).orElseThrow(),
                enclosureRequest.size(),
                enclosureRequest.maxAnimalAmount()
                );
    }

    public Enclosure getEnclosure(String enclosureId) {
        return repoEnclosures.getEnclosureById(UUID.fromString(enclosureId)).orElseThrow();
    }
}
