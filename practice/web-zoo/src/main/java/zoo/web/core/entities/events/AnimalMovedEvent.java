package zoo.web.core.entities.events;

import jakarta.annotation.Nullable;
import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.enclosure.Enclosure;
import zoo.web.core.entities.feeding.AnimalFeeding;

public record AnimalMovedEvent (Animal animal, AnimalFeeding animalFeeding, @Nullable Enclosure from, Enclosure to) {}
