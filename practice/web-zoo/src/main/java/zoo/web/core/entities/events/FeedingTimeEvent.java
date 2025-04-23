package zoo.web.core.entities.events;

import zoo.web.core.entities.feeding.FeedingSchedule;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FeedingTimeEvent (FeedingSchedule feedingSchedule, LocalDateTime curDate) {}
