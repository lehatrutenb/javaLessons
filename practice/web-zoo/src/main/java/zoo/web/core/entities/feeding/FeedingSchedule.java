package zoo.web.core.entities.feeding;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
public class FeedingSchedule {
    @Getter
    private final AnimalFeeding animal;

    @Getter
    @Setter
    @NonNull // to add to constructor
    private LocalDate nextFeedTime;

    @Getter
    @Setter
    @NonNull // to add to constructor
    private Duration feedTimePeriod;

    @Getter
    private final Food food;

    @Getter
    private final UUID id;

    public void updateNextFeedTime() {
        nextFeedTime = nextFeedTime.plus(feedTimePeriod);
    }
}
