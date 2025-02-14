package zoo.domains.animals;

import zoo.domains.nonLThings.ThingAttributes;
import zoo.interfaces.Iheribove;

public class Herbo extends Animal implements Iheribove {
    private final int kindness;
    public Herbo(HerboAttributes herboAttrs, ThingAttributes thingAttrs) {
        super(herboAttrs, thingAttrs);
        kindness = herboAttrs.GetKindness();
    }
    @Override
    public String toString() {
        return "::Herbo" + super.toString();
    }

    public int GetKindness() {
        return kindness;
    }
}
