package zoo.web.core.application_services;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import zoo.web.core.domain_services.AnimalFeedService;
import zoo.web.core.entities.enclosure.Enclosure;
import zoo.web.core.entities.events.FeedingTimeEvent;
import zoo.web.core.entities.feeding.FeedingSchedule;
import zoo.web.ishared.IrepoFeedingSchedules;
import zoo.web.ishared.ItimeGetter;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FeedingOrganizationService {
    private final IrepoFeedingSchedules repoFeedingSchedules;
    private final ItimeGetter timeGetter;
    private final AnimalFeedService animalFeedService;

    public void addFeedingSchedule(FeedingSchedule feedingSchedule) {
        repoFeedingSchedules.addFeedingSchedule(feedingSchedule);
    }

    //@Scheduled(fixedRate = 1000_000) // 1000 sec
    public void scheduleFeeding() {
        repoFeedingSchedules.getAllFeedingSchedules().forEach(
                feedingSchedule -> animalFeedService.feed(feedingSchedule, timeGetter.now())
                );
    }

    public List<FeedingSchedule> getAllFeedingSchedules() {
        return repoFeedingSchedules.getAllFeedingSchedules();
    }

    public void deleteFeedingSchedule(FeedingSchedule feedingSchedule) {
        repoFeedingSchedules.deleteFeedingSchedule(feedingSchedule);
    }
}
