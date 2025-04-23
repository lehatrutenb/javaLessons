package zoo.web.adapters.repository;

import org.springframework.stereotype.Component;
import zoo.web.core.entities.feeding.FeedingSchedule;
import zoo.web.ishared.IrepoFeedingSchedules;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RepoFeedingSchedules implements IrepoFeedingSchedules {
    List<FeedingSchedule> feedingSchedules = new ArrayList<>();

    @Override
    public void addFeedingSchedule(FeedingSchedule feedingSchedule) {
        feedingSchedules.add(feedingSchedule);
    }

    @Override
    public List<FeedingSchedule> getAllFeedingSchedules() {
        return feedingSchedules;
    }

    @Override
    public Optional<FeedingSchedule> getFeedingScheduleById(UUID feedingScheduleId) {
        return feedingSchedules.stream().filter(feedingSchedule -> feedingSchedule.getId().equals(feedingScheduleId)).findAny();
    }

    @Override
    public void deleteFeedingSchedule(FeedingSchedule feedingSchedule) {
        feedingSchedules.remove(feedingSchedule);
    }
}
