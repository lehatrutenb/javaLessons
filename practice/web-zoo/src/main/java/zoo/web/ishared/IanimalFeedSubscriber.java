package zoo.web.ishared;

import zoo.web.core.entities.events.AnimalMovedEvent;
import zoo.web.core.entities.events.FeedingTimeEvent;

public interface IanimalFeedSubscriber {
    public void run(FeedingTimeEvent feedingTimeEvent);
}
