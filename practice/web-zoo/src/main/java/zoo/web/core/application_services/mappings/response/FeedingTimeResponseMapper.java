package zoo.web.core.application_services.mappings.response;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zoo.web.core.application_services.dtos.ifactories.IEnclosureResponseFactory;
import zoo.web.core.application_services.dtos.ifactories.IfeedingTimeResponseFactory;
import zoo.web.core.application_services.dtos.response.EnclosureResponse;
import zoo.web.core.application_services.dtos.response.FeedingTimeResponse;
import zoo.web.core.entities.enclosure.Enclosure;
import zoo.web.core.entities.feeding.FeedingSchedule;

@RequiredArgsConstructor
@Component
public class FeedingTimeResponseMapper {
    private final IfeedingTimeResponseFactory feedingTimeResponseFactory;
    public FeedingTimeResponse getFeedingTimeResponse(FeedingSchedule feedingSchedule) {
        return feedingTimeResponseFactory.create(feedingSchedule);
    }
}
