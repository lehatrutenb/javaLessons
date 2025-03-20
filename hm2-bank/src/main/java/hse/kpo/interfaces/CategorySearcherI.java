package hse.kpo.interfaces;

import hse.kpo.domains.Category;

import java.util.Optional;

public interface CategorySearcherI {
    public Optional<Category> findCategoryById(String id);
}
