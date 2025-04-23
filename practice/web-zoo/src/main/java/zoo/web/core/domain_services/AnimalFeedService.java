package zoo.web.core.domain_services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import zoo.web.core.entities.events.AnimalMovedEvent;
import zoo.web.core.entities.events.FeedingTimeEvent;
import zoo.web.core.entities.feeding.FeedingSchedule;
import zoo.web.ishared.IanimalFeedService;
import zoo.web.ishared.IanimalFeedSubscriber;
import zoo.web.ishared.IfeedingTimeEventFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalFeedService implements IanimalFeedService {
    private final IfeedingTimeEventFactory feedingTimeEventFactory;
    private List<IanimalFeedSubscriber> subscribers =new ArrayList<>();
    public void subscribe(IanimalFeedSubscriber animalFeedSubscriber) {
        subscribers.add(animalFeedSubscriber);
    }

    private void publish(FeedingTimeEvent feedingTimeEvent) {
        subscribers.forEach(subscriber -> subscriber.run(feedingTimeEvent));
    }

    public boolean feed(FeedingSchedule feedingSchedule, LocalDateTime curDate) {
        if (curDate.isBefore(feedingSchedule.getNextFeedTime())) {
            return false;
            // throw new IllegalArgumentException("tried to feed before need time"); it was an idea but no really reason to throw ex
        }
        feedingSchedule.getAnimal().feed(feedingSchedule.getFood());
        feedingSchedule.updateNextFeedTime();
        publish(feedingTimeEventFactory.create(feedingSchedule, curDate));
        return true;
    }
}
