package zoo.services;

import org.springframework.stereotype.Component;
import zoo.domains.nonLThings.Thing;
import zoo.interfaces.IthingStorage;

import java.util.ArrayList;
import java.util.List;

@Component
public class ThingStorage implements IthingStorage {
    public List<Thing> things = new ArrayList<>();
    public void AddThing(Thing thing) {
        things.add(thing);
    }

    public List<Thing> GetThings() {
        return things;
    }
}
