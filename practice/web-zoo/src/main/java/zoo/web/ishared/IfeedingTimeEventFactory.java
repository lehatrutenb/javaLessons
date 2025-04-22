package zoo.web.ishared;

import zoo.web.core.entities.events.FeedingTimeEvent;
import zoo.web.core.entities.feeding.FeedingSchedule;

import java.time.LocalDate;

public interface IfeedingTimeEventFactory {
    public FeedingTimeEvent create(FeedingSchedule feedingSchedule,
                                   LocalDate curDat);
}
