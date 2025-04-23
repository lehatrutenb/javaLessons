package zoo.web.ishared;

import zoo.web.core.entities.events.FeedingTimeEvent;
import zoo.web.core.entities.feeding.FeedingSchedule;

import java.time.LocalDateTime;

public interface IanimalFeedService {
    public void subscribe(IanimalFeedSubscriber animalFeedSubscriber);
    public boolean feed(FeedingSchedule feedingSchedule, LocalDateTime curDate);
}
