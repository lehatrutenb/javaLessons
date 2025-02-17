package zoo.domains.animals;

import zoo.domains.nonLThings.ThingAttributes;
import zoo.domains.nonLThings.Thing;
import zoo.interfaces.Ianimal;
import zoo.interfaces.Ithing;

public class Animal extends Thing implements Ianimal, Ithing {
    private final AnimalAttributes animalAttrs;
    Animal(AnimalAttributes animalAttributes, ThingAttributes thingAttrs) {
        super(thingAttrs);
        this.animalAttrs = animalAttributes;
    }

    public final int GetFood() {
        return animalAttrs.GetFood();
    }
    @Override
    public String toString() {
        return "{" + super.toString() + "," + animalAttrs.toString() + "}";
    }
}
