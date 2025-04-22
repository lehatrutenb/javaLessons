package zoo.web.core.domain_services.factories;

import org.springframework.boot.context.properties.bind.Name;
import org.springframework.stereotype.Component;
import zoo.web.core.entities.feeding.Food;
import zoo.web.ishared.IfoodFactory;

@Component
public class FoodFactory implements IfoodFactory {
    @Override
    public Food create(String name) {
        return new Food(name);
    }
}
