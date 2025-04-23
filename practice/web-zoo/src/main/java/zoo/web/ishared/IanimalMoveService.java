package zoo.web.ishared;

import jakarta.annotation.Nullable;
import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.enclosure.Enclosure;
import zoo.web.core.entities.events.AnimalMovedEvent;
import zoo.web.core.entities.feeding.AnimalFeeding;

public interface IanimalMoveService {
    public void subscribe(IanimalMoveSubscriber animalMoveSubscriber);

    public boolean checkCanMove(AnimalFeeding animalFeeding, Enclosure to);

    public void move(Animal animal, AnimalFeeding animalFeeding, @Nullable Enclosure from, Enclosure to) throws IllegalArgumentException;
}
