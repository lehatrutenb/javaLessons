package zoo.web.ishared;

import lombok.NonNull;
import zoo.web.core.entities.feeding.AnimalFeeding;
import zoo.web.core.entities.feeding.FeedingSchedule;
import zoo.web.core.entities.feeding.Food;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IfeedingScheduleFactory {
    public FeedingSchedule create(AnimalFeeding animal,
                                  @NonNull LocalDateTime nextFeedTime,
                                  @NonNull Duration feedTimePeriod,
                                  Food food);
}
