package hse.kpo.storages;

import hse.kpo.domains.Category;
import hse.kpo.domains.CategoryReport;
import hse.kpo.interfaces.CategorySearcherI;
import hse.kpo.interfaces.CategoryStorageI;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class CategoryStorage implements CategoryStorageI, CategorySearcherI {
    private final CategoryReport categoryReport = new CategoryReport();
    @Override
    public Optional<Category> findCategoryById(String id) {
        return categoryReport.getReport().stream().filter(
                category -> Objects.equals(category.getId(), id)).findFirst();
    }

    @Override
    public void add(Category category) {
        categoryReport.addReportElement(category);
    }

    @Override
    public void addReport(CategoryReport categoryReport) {
        categoryReport.getReport().forEach(this::add);
    }

    @Override
    public CategoryReport getReport() {
        return categoryReport;
    }
}
