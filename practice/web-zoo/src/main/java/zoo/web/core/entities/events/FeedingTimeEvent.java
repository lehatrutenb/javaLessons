package zoo.web.core.entities.events;

import zoo.web.core.entities.feeding.FeedingSchedule;

import java.time.LocalDate;

public record FeedingTimeEvent (FeedingSchedule feedingSchedule, LocalDate curDate) {}
