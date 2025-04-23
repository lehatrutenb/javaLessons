package zoo.web.ishared;

import zoo.web.core.application_services.AnimalTransferService;
import zoo.web.core.application_services.EnclosureManagementService;
import zoo.web.core.application_services.FeedingOrganizationService;
import zoo.web.core.application_services.ZooStatisticsService;
import zoo.web.core.application_services.dtos.request.AnimalRequest;
import zoo.web.core.application_services.dtos.request.EnclosureRequest;
import zoo.web.core.application_services.dtos.request.FeedingTimeRequest;
import zoo.web.core.application_services.dtos.response.AnimalResponse;
import zoo.web.core.application_services.dtos.response.EnclosureResponse;
import zoo.web.core.application_services.dtos.response.FeedingTimeResponse;
import zoo.web.core.application_services.mappings.request.AnimalRequestMapper;
import zoo.web.core.application_services.mappings.request.EnclosureRequestMapper;
import zoo.web.core.application_services.mappings.request.FeedingTimeRequestMapper;
import zoo.web.core.application_services.mappings.response.AnimalResponseMapper;
import zoo.web.core.application_services.mappings.response.EnclosureResponseMapper;
import zoo.web.core.application_services.mappings.response.FeedingTimeResponseMapper;
import zoo.web.core.entities.AllAnimalInfo;
import zoo.web.core.entities.enclosure.Enclosure;
import zoo.web.core.entities.feeding.FeedingSchedule;

import java.util.List;
import java.util.Optional;

public interface IapplicationFacade {
    public List<AnimalResponse> getAnimals();

    public List<EnclosureResponse> getEnclosures();

    public AnimalResponse addAnimal(AnimalRequest animalRequest, String enclosureId) throws Throwable;

    public AnimalResponse addAnimalToAny(AnimalRequest animalRequest) throws Throwable;

    public void moveAnimal(String animalId, String enclosureToId) throws Throwable;

    public void deleteAnimal(String animalId);

    public EnclosureResponse addEnclosure(EnclosureRequest enclosureRequest);

    public void deleteEnclosure(String enclosureId);

    public FeedingTimeResponse addFeedingSchedule(FeedingTimeRequest feedingTimeRequest);

    public void deleteFeedingSchedule(String feedingScheduleId);

    public List<FeedingTimeResponse> getFeedingSchedules();

    public void runFeeding();
}
