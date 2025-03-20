package hse.kpo.factories;

import hse.kpo.domains.Category;
import hse.kpo.enums.OperationType;
import org.springframework.stereotype.Component;
//import hse.kpo.observers.MeasureDurAspect;

@Component
public class CategoryFactory {
    public Category create(OperationType operationType, String id, String name) {
        return new Category(operationType, id, name);
    }

    public Category fromCsvString(String s) {
        String[] attrs = s.split(",");
        return new Category(OperationType.valueOf(attrs[0]), attrs[1], attrs[2]);
    }
}
