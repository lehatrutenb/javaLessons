package zoo.factories;

import org.springframework.stereotype.Component;
import zoo.domains.nonLThings.Table;
import zoo.domains.nonLThings.Thing;
import zoo.domains.nonLThings.ThingAttributes;
import zoo.interfaces.IthingFactory;

@Component
public class TableFactory implements IthingFactory<ThingAttributes> {
    public Thing CreateThing(ThingAttributes thingAttrs) {
        return new Table(thingAttrs);
    }
}
