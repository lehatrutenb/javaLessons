package hse.kpo.dtos;

import hse.kpo.domains.Category;
import hse.kpo.domains.Operation;
import kotlin.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OperationsGrouppedByCategories {
    List<List<Operation>> operations = new ArrayList<>();
    List<Category> categories = new ArrayList<>();

    public void add(List<Operation> operations, Category category) {
        /*int categoryInd = -1;
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId().equals(category.getId())) {
                categoryInd = i;
                break;
            }
        }*/
        int categoryInd = categories.indexOf(category);
        if (categoryInd == -1) {
            categories.add(category);
            this.operations.add(new ArrayList<>());
            categoryInd = categories.size() - 1;
        }

        int finalCategoryInd = categoryInd;
        operations.forEach(operation -> this.operations.get(finalCategoryInd).add(operation));
    }

    public Optional<Pair<List<Operation>, Category>> get() {
        if (operations.isEmpty()) {
            return Optional.empty();
        }
        Pair<List<Operation>, Category> res = new Pair<>(operations.getFirst(), categories.getFirst());
        operations.removeFirst();
        categories.removeFirst();
        return Optional.of(res);
    }
}
