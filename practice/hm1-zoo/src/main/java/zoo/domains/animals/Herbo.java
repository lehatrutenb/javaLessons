package zoo.domains.animals;

import zoo.domains.nonLThings.ThingAttributes;
import zoo.interfaces.Iheribove;
import zoo.types.MonkeyType;
import zoo.types.RabbitType;

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

    public static boolean IsAnimalHerbo(String animalName) {
        return animalName.equals(RabbitType.Name) || animalName.equals(MonkeyType.Name);
    }
}
