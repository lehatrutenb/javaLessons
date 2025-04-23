package zoo.web.ishared;

import zoo.web.core.entities.events.FeedingTimeEvent;
import zoo.web.core.entities.feeding.FeedingSchedule;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IfeedingTimeEventFactory {
    public FeedingTimeEvent create(FeedingSchedule feedingSchedule,
                                   LocalDateTime curDat);
}
