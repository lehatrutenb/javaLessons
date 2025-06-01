package zoo.web.core.application_services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zoo.web.core.domain_services.AnimalMoveService;
import zoo.web.core.entities.events.AnimalMovedEvent;
import zoo.web.core.entities.AllAnimalInfo;
import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.enclosure.Enclosure;
import zoo.web.core.entities.feeding.AnimalFeeding;
import zoo.web.core.entities.healing.AnimalHealing;
import zoo.web.ishared.IrepoAnimalsAll;
import zoo.web.ishared.IrepoEnclosures;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AnimalTransferService {
    private final IrepoAnimalsAll repoAnimals;
    private final IrepoEnclosures repoEnclosures;
    private final AnimalMoveService animalMoveService;

    public void addAnimal(AllAnimalInfo allAnimalInfo, Enclosure to) throws Throwable {
        repoAnimals.addAnimal(allAnimalInfo.animal());
        repoAnimals.addAnimalFeeding(allAnimalInfo.animalFeeding());
        repoAnimals.addAnimalHealing(allAnimalInfo.animalHealing());

        animalMoveService.move(allAnimalInfo.animal(), allAnimalInfo.animalFeeding(), null, to);
    }

    public boolean addAnimalToAny(AllAnimalInfo allAnimalInfo) throws Throwable {
        Optional<Enclosure> enclosure = repoEnclosures.getAllEnclosures().stream().filter(
                curEnclosure -> animalMoveService.checkCanMove(allAnimalInfo.animalFeeding(), curEnclosure)
        ).findFirst();
        if (enclosure.isEmpty()) {
            return false;
        }

        repoAnimals.addAnimal(allAnimalInfo.animal());
        repoAnimals.addAnimalFeeding(allAnimalInfo.animalFeeding());
        repoAnimals.addAnimalHealing(allAnimalInfo.animalHealing());

        animalMoveService.move(allAnimalInfo.animal(), allAnimalInfo.animalFeeding(), null, enclosure.get());
        return true;
    }

    public void moveAnimal(Animal animal, Enclosure to) throws Throwable {
        AnimalFeeding animalFeeding = repoAnimals.getAnimalFeedingById(animal.getId()).orElseThrow();
        Enclosure from = repoEnclosures.getAllEnclosures().stream().filter(enclosure -> enclosure.hasAnimal(animal)).findAny().orElse(null);
        animalMoveService.move(animal, animalFeeding, from, to);
    }

    public void deleteAnimal(Animal animal) {
        AnimalFeeding animalFeeding = repoAnimals.getAnimalFeedingById(animal.getId()).orElseThrow();
        AnimalHealing animalHealing = repoAnimals.getAnimalHealingById(animal.getId()).orElseThrow();


        repoAnimals.deleteAnimal(animal);
        repoAnimals.deleteAnimalFeeding(animalFeeding);
        repoAnimals.deleteAnimalHealing(animalHealing);
    }
}
