package zoo.web.core.application_services;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import zoo.web.core.entities.events.AnimalMovedEvent;
import zoo.web.core.entities.events.FeedingTimeEvent;
import zoo.web.ishared.IanimalFeedSubscriber;
import zoo.web.ishared.IanimalMoveService;
import zoo.web.ishared.IanimalMoveSubscriber;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ZooStatisticsService implements IanimalFeedSubscriber, IanimalMoveSubscriber {
    @Getter
    private List<AnimalMovedEvent> animalMovedEvents = new ArrayList<>();
    @Getter
    private List<FeedingTimeEvent> feedingTimeEvents = new ArrayList<>();

    public void run(AnimalMovedEvent animalMovedEvent) {
        animalMovedEvents.add(animalMovedEvent);
    }

    @Override
    public void run(FeedingTimeEvent feedingTimeEvent) {
        feedingTimeEvents.add(feedingTimeEvent);
    }
}
