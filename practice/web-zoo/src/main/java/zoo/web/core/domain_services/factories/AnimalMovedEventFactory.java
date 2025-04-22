package zoo.web.core.domain_services.factories;

import jakarta.annotation.Nullable;
import org.springframework.stereotype.Component;
import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.enclosure.Enclosure;
import zoo.web.core.entities.events.AnimalMovedEvent;
import zoo.web.core.entities.feeding.AnimalFeeding;
import zoo.web.ishared.IanimalMovedEventFactory;

@Component
public class AnimalMovedEventFactory implements IanimalMovedEventFactory {
    @Override
    public AnimalMovedEvent create(Animal animal, AnimalFeeding animalFeeding, @Nullable Enclosure from, Enclosure to) {
        return new AnimalMovedEvent(animal, animalFeeding, from, to);
    }
}
