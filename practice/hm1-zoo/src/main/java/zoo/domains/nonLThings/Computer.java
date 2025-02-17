package zoo.domains.nonLThings;

public class Computer extends Thing {
    public Computer(ThingAttributes thingAttributes) {
        super(thingAttributes);
    }
    @Override
    public String toString() {
        return "Computer" + super.toString();
    }
}
