package zoo.web.ishared;

import zoo.web.core.entities.enclosure.Enclosure;
import zoo.web.core.entities.feeding.FeedingSchedule;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IrepoFeedingSchedules {
    public void addFeedingSchedule(FeedingSchedule feedingSchedule);
    public List<FeedingSchedule> getAllFeedingSchedules();
    public Optional<FeedingSchedule> getFeedingScheduleById(UUID feedingScheduleId);
    public void deleteFeedingSchedule(FeedingSchedule feedingSchedule);
}
