package zoo.domains.animals;

import zoo.domains.nonLThings.ThingAttributes;

public class Rabbit extends Herbo {
    public Rabbit(HerboAttributes herboAttrs, ThingAttributes thingAttrs) {
        super(herboAttrs, thingAttrs);
    }
    @Override
    public String toString() {
        return "Rabbit" + super.toString();
    }
}
