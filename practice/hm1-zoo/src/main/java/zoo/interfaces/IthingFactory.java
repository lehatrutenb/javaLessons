package zoo.interfaces;

import zoo.domains.animals.Animal;
import zoo.domains.nonLThings.Thing;
import zoo.domains.nonLThings.ThingAttributes;

public interface IthingFactory<TthingAttrs> {
    public Thing CreateThing(TthingAttrs thingAttrs);
}