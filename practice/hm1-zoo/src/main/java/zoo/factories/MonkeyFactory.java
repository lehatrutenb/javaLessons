package zoo.factories;

import org.springframework.stereotype.Component;
import zoo.domains.animals.*;
import zoo.domains.nonLThings.ThingAttributes;
import zoo.interfaces.IanimalFactory;

@Component
public class MonkeyFactory implements IanimalFactory<HerboAttributes> {
    public Animal CreateAnimal(HerboAttributes animalAttributes, ThingAttributes thingAttrs) {
        return new Monkey(animalAttributes, thingAttrs);
    }
}