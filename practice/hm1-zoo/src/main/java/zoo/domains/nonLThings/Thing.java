package zoo.domains.nonLThings;

import zoo.interfaces.Ithing;

public class Thing implements Ithing {
    private final ThingAttributes thingAttributes;
    public int GetNumber() {
        return thingAttributes.GetNumber();
    }
    public Thing(ThingAttributes thingAttributes) {
        this.thingAttributes = thingAttributes;
    }
    @Override
    public String toString() {
        return "{" + thingAttributes.toString() + "}";
    }
}
