package zoo.web.adapters.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import zoo.web.core.application_services.Facade;
import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.enclosure.Enclosure;
import zoo.web.ishared.IrepoEnclosures;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RepoEnclosures implements IrepoEnclosures {
    List<Enclosure> enclosures = new ArrayList<>();

    @Override
    public void addEnclosure(Enclosure enclosure) {
        enclosures.add(enclosure);
    }

    @Override
    public List<Enclosure> getAllEnclosures() {
        return enclosures;
    }

    @Override
    public Optional<Enclosure> getEnclosureById(UUID enclosureId) {
        return enclosures.stream().filter(enclosure -> enclosure.getId().equals(enclosureId)).findAny();
    }

    @Override
    public void deleteEnclosure(Enclosure enclosure) {
        enclosures.remove(enclosure);
    }
}
