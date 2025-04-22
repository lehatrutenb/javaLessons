package zoo.web.ishared;

import lombok.NonNull;
import zoo.web.core.entities.feeding.AnimalFeeding;
import zoo.web.core.entities.feeding.FeedingSchedule;
import zoo.web.core.entities.feeding.Food;

import java.time.Duration;
import java.time.LocalDate;

public interface IfeedingScheduleFactory {
    public FeedingSchedule create(AnimalFeeding animal,
                                  @NonNull LocalDate nextFeedTime,
                                  @NonNull Duration feedTimePeriod,
                                  Food food);
}
