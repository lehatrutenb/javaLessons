package zoo.web.core.domain_services;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.enclosure.Enclosure;
import zoo.web.core.entities.events.AnimalMovedEvent;
import zoo.web.core.entities.feeding.AnimalFeeding;
import zoo.web.ishared.IanimalFeedSubscriber;
import zoo.web.ishared.IanimalMoveSubscriber;
import zoo.web.ishared.IanimalMovedEventFactory;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AnimalMoveService {
    private final IanimalMovedEventFactory animalMovedEventFactory;
    private List<IanimalMoveSubscriber> subscribers = new ArrayList<>();
    public void subscribe(IanimalMoveSubscriber animalMoveSubscriber) {
        subscribers.add(animalMoveSubscriber);
    }

    private void publish(AnimalMovedEvent animalMovedEvent) {
        subscribers.forEach(subscriber -> subscriber.run(animalMovedEvent));
    }

    public boolean checkCanMove(AnimalFeeding animalFeeding, Enclosure to) {
        return animalFeeding.getAnimalEatType().equals(to.getAnimalEatType());
    }
    public void move(Animal animal, AnimalFeeding animalFeeding, @Nullable Enclosure from, Enclosure to) throws IllegalArgumentException {
        if (!animal.getId().equals(animalFeeding.getId())) {
            throw new IllegalArgumentException("expected same animal");
        }
        if (!checkCanMove(animalFeeding, to)) {
            throw new IllegalArgumentException("can't move animal to that enclosure");
        }

        if (from != null) {
            from.removeAnimal(animal);
        }
        to.addAnimal(animal);

        publish(animalMovedEventFactory.create(animal, animalFeeding, from, to));
    }
}
