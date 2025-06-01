package zoo.web.core.domain_services.factories;

import org.springframework.stereotype.Component;
import zoo.web.core.entities.animals.AnimalEatType;
import zoo.web.core.entities.enclosure.Enclosure;
import zoo.web.ishared.IenclosureFactory;

import java.util.UUID;

@Component
public class EnclosureFactory implements IenclosureFactory {
    public Enclosure create(AnimalEatType animalEatType,
                            int size,
                            int maxAnimalAmount) {
        return new Enclosure(UUID.randomUUID(), animalEatType, size, maxAnimalAmount);
    }
}
