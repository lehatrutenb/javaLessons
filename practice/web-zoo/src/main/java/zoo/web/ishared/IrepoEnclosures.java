package zoo.web.ishared;

import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.enclosure.Enclosure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IrepoEnclosures {
    public void addEnclosure(Enclosure enclosure);
    public List<Enclosure> getAllEnclosures();
    public Optional<Enclosure> getEnclosureById(UUID enclosureId);
    // public void DeleteAnimalFromAllEnclosures(Animal animal);
    public void deleteEnclosure(Enclosure enclosure);
}
