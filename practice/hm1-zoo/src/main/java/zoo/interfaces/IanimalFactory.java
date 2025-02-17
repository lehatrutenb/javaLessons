package zoo.interfaces;

import zoo.domains.animals.Animal;
import zoo.domains.nonLThings.ThingAttributes;

public interface IanimalFactory<TanimalAttrs> {
    public Animal CreateAnimal(TanimalAttrs animalParams, ThingAttributes thingAttributes);
}