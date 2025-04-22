package zoo.web.ishared;

import zoo.web.core.entities.events.AnimalMovedEvent;

public interface IanimalMoveSubscriber {
    public void run(AnimalMovedEvent animalMovedEvent);
}
