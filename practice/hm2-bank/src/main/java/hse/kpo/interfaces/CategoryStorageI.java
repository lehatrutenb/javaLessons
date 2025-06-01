package hse.kpo.interfaces;

import hse.kpo.domains.BankAccount;
import hse.kpo.domains.BankAccountReport;
import hse.kpo.domains.Category;
import hse.kpo.domains.CategoryReport;

import java.util.Optional;

public interface CategoryStorageI {
    public void add(Category category);
    public void addReport(CategoryReport categoryReport);
    public CategoryReport getReport();
}