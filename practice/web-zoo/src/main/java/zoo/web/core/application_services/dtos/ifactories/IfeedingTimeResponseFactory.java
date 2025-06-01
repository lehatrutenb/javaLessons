package zoo.web.core.application_services.dtos.ifactories;

import zoo.web.core.application_services.dtos.response.FeedingTimeResponse;
import zoo.web.core.entities.feeding.FeedingSchedule;

public interface IfeedingTimeResponseFactory {
    public FeedingTimeResponse create(FeedingSchedule feedingSchedule);
}
