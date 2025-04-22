package zoo.web.core.domain_services.factories;

import org.springframework.stereotype.Component;
import zoo.web.core.entities.animals.AnimalType;
import zoo.web.ishared.IanimalTypeFactory;

@Component
public class AnimalTypeFactory implements IanimalTypeFactory {

    @Override
    public AnimalType create(String type) {
        return new AnimalType(type);
    }
}
