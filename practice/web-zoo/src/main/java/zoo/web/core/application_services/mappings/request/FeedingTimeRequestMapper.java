package zoo.web.core.application_services.mappings.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zoo.web.core.application_services.dtos.request.EnclosureRequest;
import zoo.web.core.application_services.dtos.request.FeedingTimeRequest;
import zoo.web.core.domain_services.factories.FoodFactory;
import zoo.web.core.entities.animals.AnimalEatType;
import zoo.web.core.entities.enclosure.Enclosure;
import zoo.web.core.entities.feeding.FeedingSchedule;
import zoo.web.ishared.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class FeedingTimeRequestMapper {
    private final IfeedingScheduleFactory feedingScheduleFactory;
    private final IfoodFactory foodFactory;
    private final IrepoAnimalsFeeding repoAnimalsFeeding;
    private final IrepoFeedingSchedules repoFeedingSchedules;

    public FeedingSchedule getFeeding(FeedingTimeRequest feedingTimeRequest) {
        return feedingScheduleFactory.create(
                repoAnimalsFeeding.getAnimalFeedingById(UUID.fromString(feedingTimeRequest.animalId())).orElseThrow(),
                LocalDateTime.parse(feedingTimeRequest.firstFeedTime(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),
                Duration.ofHours(feedingTimeRequest.periodH()),
                foodFactory.create(feedingTimeRequest.food())
        );
    }

    public FeedingSchedule getFeedingSchedule(String feedingScheduleId) {
        return repoFeedingSchedules.getFeedingScheduleById(UUID.fromString(feedingScheduleId)).orElseThrow();
    }
}