package zoo.web.ishared;

import zoo.web.core.entities.feeding.Food;

public interface IfoodFactory {
    public Food create(String name);
}
