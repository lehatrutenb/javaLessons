package zoo.factories;

import org.springframework.stereotype.Component;
import zoo.domains.animals.Animal;
import zoo.domains.animals.HerboAttributes;
import zoo.domains.animals.Monkey;
import zoo.domains.animals.Rabbit;
import zoo.domains.nonLThings.ThingAttributes;
import zoo.interfaces.IanimalFactory;

@Component
public class RabbitFactory  implements IanimalFactory<HerboAttributes> {
    public Animal CreateAnimal(HerboAttributes animalAttributes, ThingAttributes thingAttrs) {
        return new Rabbit(animalAttributes, thingAttrs);
    }
}