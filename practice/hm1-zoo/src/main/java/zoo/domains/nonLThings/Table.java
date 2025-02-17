package zoo.domains.nonLThings;

public class Table extends Thing {
    public Table(ThingAttributes thingAttributes) {
        super(thingAttributes);
    }
    @Override
    public String toString() {
        return "Table" + super.toString();
    }
}
