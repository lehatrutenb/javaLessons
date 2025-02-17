package zoo.domains.nonLThings;

public class ThingAttributes {
    private int number;
    private String naming;
    public ThingAttributes(int number, String naming) {
        this.number = number;
        this.naming = naming;
    }
    public int GetNumber() {
        return number;
    }
    public String GetNaming() {
        return naming;
    }
    @Override
    public String toString() {
        return "number=" + GetNumber() + ", naming=" + GetNaming() + ",";
    }
}
