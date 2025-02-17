package zoo.domains.animals;

public class HerboAttributes extends AnimalAttributes {
    private final int kindness;
    public HerboAttributes(int food, int kindness) {
        super(food);
        this.kindness = kindness;
    }
    public int GetKindness() {
        return kindness;
    }
    @Override
    public String toString() {
        return super.toString() + "kindness=" + GetKindness() + ",";
    }
}
