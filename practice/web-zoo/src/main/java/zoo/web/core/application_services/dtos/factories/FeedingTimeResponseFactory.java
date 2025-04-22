package zoo.web.core.application_services.dtos.factories;

import org.springframework.stereotype.Component;
import zoo.web.core.application_services.dtos.ifactories.IfeedingTimeResponseFactory;
import zoo.web.core.application_services.dtos.response.FeedingTimeResponse;
import zoo.web.core.entities.feeding.FeedingSchedule;

@Component
public class FeedingTimeResponseFactory implements IfeedingTimeResponseFactory {
    @Override
    public FeedingTimeResponse create(FeedingSchedule feedingSchedule) {
        return new FeedingTimeResponse(feedingSchedule.getId().toString());
    }
}
