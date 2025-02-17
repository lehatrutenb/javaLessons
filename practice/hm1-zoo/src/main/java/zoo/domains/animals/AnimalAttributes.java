package zoo.domains.animals;

public class AnimalAttributes {
    private final int food;
    public AnimalAttributes(int food) {
        this.food = food;
    }
    public int GetFood() {
        return food;
    }
    @Override
    public String toString() {
        return "food=" + GetFood() + ",";
    }
}
