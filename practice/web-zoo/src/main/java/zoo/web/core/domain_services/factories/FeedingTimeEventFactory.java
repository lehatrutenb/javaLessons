package zoo.web.core.domain_services.factories;

import org.springframework.stereotype.Component;
import zoo.web.core.entities.events.FeedingTimeEvent;
import zoo.web.core.entities.feeding.FeedingSchedule;
import zoo.web.ishared.IfeedingTimeEventFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class FeedingTimeEventFactory implements IfeedingTimeEventFactory {

    @Override
    public FeedingTimeEvent create(FeedingSchedule feedingSchedule, LocalDateTime curDat) {
        return new FeedingTimeEvent(feedingSchedule, curDat);
    }
}
