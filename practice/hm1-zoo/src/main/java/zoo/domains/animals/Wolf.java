package zoo.domains.animals;

import zoo.domains.nonLThings.ThingAttributes;

public class Wolf extends Predator {
    public Wolf(AnimalAttributes animalAttrs, ThingAttributes thingAttrs) {
        super(animalAttrs, thingAttrs);
    }
    @Override
    public String toString() {
        return "Wolf" + super.toString();
    }
}
