package zoo.domains.animals;

import zoo.domains.nonLThings.ThingAttributes;

public class Monkey extends Herbo {

    public Monkey(HerboAttributes herboAttrs, ThingAttributes thingAttrs) {
        super(herboAttrs, thingAttrs);
    }
    @Override
    public String toString() {
        return "Monkey" + super.toString();
    }
}
