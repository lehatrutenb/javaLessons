package zoo.web.core.application_services.mappings.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zoo.web.core.application_services.dtos.request.AnimalRequest;
import zoo.web.core.entities.AllAnimalInfo;
import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.animals.AnimalEatType;
import zoo.web.core.entities.animals.AnimalMale;
import zoo.web.core.entities.animals.AnimalType;
import zoo.web.core.entities.enclosure.Enclosure;
import zoo.web.core.entities.feeding.AnimalFeeding;
import zoo.web.core.entities.healing.AnimalHealing;
import zoo.web.ishared.IanimalFactory;
import zoo.web.ishared.IanimalTypeFactory;
import zoo.web.ishared.IfoodFactory;
import zoo.web.ishared.IrepoAnimalsAll;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class AnimalRequestMapper {
    private final IanimalFactory animalFactory;
    private final IfoodFactory foodFactory;
    private final IanimalTypeFactory animalTypeFactory;
    private final IrepoAnimalsAll repoAnimalsAll;

    public AllAnimalInfo getAllAnimalInfo(AnimalRequest animalRequest) {
        return animalFactory.create(AnimalEatType.find(animalRequest.animalEatType()).orElseThrow(),
                animalTypeFactory.create(animalRequest.animalType()),
                AnimalMale.find(animalRequest.animalMale()).orElseThrow(),
                foodFactory.create(animalRequest.favoriteFood()),
                LocalDate.parse(animalRequest.birthday(), DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                Objects.equals(animalRequest.isHealthy(), "true"));
    }

    public Animal getAnimal(AnimalRequest animalRequest) {
        return getAllAnimalInfo(animalRequest).animal();
    }

    public AnimalFeeding getAnimalFeeding(AnimalRequest animalRequest) {
        return getAllAnimalInfo(animalRequest).animalFeeding();
    }

    public AnimalHealing getAnimalHealing(AnimalRequest animalRequest) {
        return getAllAnimalInfo(animalRequest).animalHealing();
    }

    public Animal getAnimal(String animalId) {
        return repoAnimalsAll.getAnimalById(UUID.fromString(animalId)).orElseThrow();
    }

    public AnimalFeeding getAnimalFeeding(String animalId) {
        return repoAnimalsAll.getAnimalFeedingById(UUID.fromString(animalId)).orElseThrow();
    }

    public AnimalHealing getAnimalHealing(String animalId) {
        return repoAnimalsAll.getAnimalHealingById(UUID.fromString(animalId)).orElseThrow();
    }
}
