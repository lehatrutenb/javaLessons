package zoo.web.ishared;

import jakarta.annotation.Nullable;
import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.enclosure.Enclosure;
import zoo.web.core.entities.events.AnimalMovedEvent;
import zoo.web.core.entities.feeding.AnimalFeeding;

public interface IanimalMovedEventFactory {
    public AnimalMovedEvent create(Animal animal,
                                    AnimalFeeding animalFeeding,
                                    @Nullable Enclosure from,
                                    Enclosure to);
}
