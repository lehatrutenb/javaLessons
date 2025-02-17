package zoo.domains.animals;

import zoo.domains.nonLThings.ThingAttributes;

public class Predator extends Animal {
    Predator(AnimalAttributes animalAttrs, ThingAttributes thingAttrs) {
        super(animalAttrs, thingAttrs);
    }
    @Override
    public String toString() {
        return "::Predator" + super.toString();
    }
}
