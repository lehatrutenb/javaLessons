package zoo.interfaces;

import zoo.domains.animals.Animal;
import zoo.domains.nonLThings.Thing;

import java.util.List;

public interface IthingStorage {
    public void AddThing(Thing thing);
    public List<Thing> GetThings();
}
