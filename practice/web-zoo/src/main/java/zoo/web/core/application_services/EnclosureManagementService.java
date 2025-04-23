package zoo.web.core.application_services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zoo.web.core.entities.events.AnimalMovedEvent;
import zoo.web.core.entities.AllAnimalInfo;
import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.enclosure.Enclosure;
import zoo.web.core.entities.feeding.AnimalFeeding;
import zoo.web.ishared.IrepoAnimalsAll;
import zoo.web.ishared.IrepoEnclosures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EnclosureManagementService {
    private final IrepoEnclosures repoEnclosures;

    public void addEnclosure(Enclosure enclosure) {
       repoEnclosures.addEnclosure(enclosure);
    }

    public void deleteEnclosure(Enclosure enclosure) {
        if (enclosure.getCurAnimalAmount() != 0) {
            throw new IllegalArgumentException("tried to delete non-empty enclosure");
        }

        repoEnclosures.deleteEnclosure(enclosure);
    }

    public Optional<Enclosure> getEnclosureByAnimal(Animal animal) {
        return repoEnclosures.getAllEnclosures().stream().filter(enclosure -> enclosure.hasAnimal(animal)).findAny();
    }

    public List<Enclosure> getEnclosures() {
        return repoEnclosures.getAllEnclosures();
    }

    public void deleteAnimalFromEnclosures(Animal animal) {
        repoEnclosures.getAllEnclosures().forEach(enclosure -> enclosure.removeAnimal(animal));
    }
}
