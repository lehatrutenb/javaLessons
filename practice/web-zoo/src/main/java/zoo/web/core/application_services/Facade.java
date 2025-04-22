package zoo.web.core.application_services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
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
import zoo.web.core.domain_services.factories.AnimalFactory;
import zoo.web.core.domain_services.factories.FeedingScheduleFactory;
import zoo.web.core.entities.AllAnimalInfo;
import zoo.web.core.entities.enclosure.Enclosure;
import zoo.web.core.entities.feeding.FeedingSchedule;
import zoo.web.ishared.IanimalFactory;
import zoo.web.ishared.IfeedingScheduleFactory;

// use dtos + mappings
@Component
@RequiredArgsConstructor
public class Facade {
    private final IfeedingScheduleFactory feedingScheduleFactory;
    private final IanimalFactory animalFactory;
    private final AnimalTransferService animalTransferService;
    private final FeedingOrganizationService feedingOrganizationService;
    private final ZooStatisticsService zooStatisticsService;
    private final EnclosureManagementService enclosureManagementService;

    private final AnimalRequestMapper animalRequestMapper;
    private final EnclosureRequestMapper enclosureRequestMapper;
    private final FeedingTimeRequestMapper feedingTimeRequestMapper;

    private final AnimalResponseMapper animalResponseMapper;
    private final EnclosureResponseMapper enclosureResponseMapper;
    private final FeedingTimeResponseMapper feedingTimeResponseMapper;

    public AnimalResponse addAnimal(AnimalRequest animalRequest, String enclosureId) throws Throwable {
        AllAnimalInfo animal = animalRequestMapper.getAllAnimalInfo(animalRequest);
        Enclosure enclosure = enclosureRequestMapper.getEnclosure(enclosureId);
        animalTransferService.addAnimal(animal, enclosure);
        return animalResponseMapper.getAnimalResponse(animal, enclosure);
    }

    public void addAnimalToAny(AnimalRequest animalRequest) throws Throwable {
        animalTransferService.addAnimalToAny(animalRequestMapper.getAllAnimalInfo(animalRequest));
    }

    public void moveAnimal(String animalId, String enclosureToId) throws Throwable {
        animalTransferService.moveAnimal(
                animalRequestMapper.getAnimal(animalId),
                enclosureRequestMapper.getEnclosure(enclosureToId)
        );
    }

    public void deleteAnimal(String animalId) {
        animalTransferService.deleteAnimal(animalRequestMapper.getAnimal(animalId));
    }

    public EnclosureResponse addEnclosure(EnclosureRequest enclosureRequest) {
        Enclosure enclosure = enclosureRequestMapper.getEnclosure(enclosureRequest);
        enclosureManagementService.addEnclosure(
            enclosure
        );
        return enclosureResponseMapper.getAnimalResponse(enclosure);
    }

    public void deleteEnclosure(String enclosureId) {
        enclosureManagementService.deleteEnclosure(
                enclosureRequestMapper.getEnclosure(enclosureId)
        );
    }

    public FeedingTimeResponse addFeedingSchedule(FeedingTimeRequest feedingTimeRequest) {
        FeedingSchedule feedingSchedule = feedingTimeRequestMapper.getFeeding(feedingTimeRequest);
        feedingOrganizationService.addFeedingSchedule(
                feedingSchedule
        );
        return feedingTimeResponseMapper.getFeedingTimeResponse(feedingSchedule);
    }

    public void deleteFeedingSchedule(String feedingScheduleId) {
        feedingOrganizationService.deleteFeedingSchedule(
                feedingTimeRequestMapper.getFeedingSchedule(feedingScheduleId)
        );
    }
}
