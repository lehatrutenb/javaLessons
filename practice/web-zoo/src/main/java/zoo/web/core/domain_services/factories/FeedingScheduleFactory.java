package zoo.web.core.domain_services.factories;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import zoo.web.core.entities.feeding.AnimalFeeding;
import zoo.web.core.entities.feeding.FeedingSchedule;
import zoo.web.core.entities.feeding.Food;
import zoo.web.ishared.IfeedingScheduleFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.Duration;
import java.util.UUID;

@Component
public class FeedingScheduleFactory implements IfeedingScheduleFactory {
    public FeedingSchedule create(AnimalFeeding animal,
                                  @NonNull LocalDateTime nextFeedTime,
                                  @NonNull Duration feedTimePeriod,
                                  Food food) {
        return new FeedingSchedule(animal, nextFeedTime, feedTimePeriod, food, UUID.randomUUID());
    }
}
