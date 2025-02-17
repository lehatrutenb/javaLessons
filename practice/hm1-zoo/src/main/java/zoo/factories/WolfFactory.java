package zoo.factories;

import org.springframework.stereotype.Component;
import zoo.domains.animals.Animal;
import zoo.domains.animals.Wolf;
import zoo.domains.nonLThings.Table;
import zoo.domains.nonLThings.Thing;
import zoo.domains.nonLThings.ThingAttributes;
import zoo.interfaces.IanimalFactory;
import zoo.interfaces.IthingFactory;
import zoo.domains.animals.AnimalAttributes;

@Component
public class WolfFactory implements IanimalFactory<AnimalAttributes> {
    public Animal CreateAnimal(AnimalAttributes animalAttributes, ThingAttributes thingAttrs) {
        return new Wolf(animalAttributes, thingAttrs);
    }
}