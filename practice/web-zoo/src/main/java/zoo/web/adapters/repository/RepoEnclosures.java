package zoo.web.adapters.repository;

import org.springframework.stereotype.Component;
import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.enclosure.Enclosure;
import zoo.web.ishared.IrepoEnclosures;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RepoEnclosures implements IrepoEnclosures {
    List<Enclosure> enclosures = List.of();

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
        return enclosures.stream().filter(enclosure -> enclosure.getId() == enclosureId).findAny();
    }

    @Override
    public void deleteEnclosure(Enclosure enclosure) {
        enclosures.remove(enclosure);
    }
}
