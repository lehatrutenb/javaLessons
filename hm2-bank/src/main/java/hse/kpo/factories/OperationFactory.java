package hse.kpo.factories;

import hse.kpo.domains.BankAccount;
import hse.kpo.domains.Category;
import hse.kpo.domains.Operation;
import hse.kpo.domains.OperationMemento;
import hse.kpo.interfaces.BankAccountSearcherI;
import hse.kpo.interfaces.CategorySearcherI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class OperationFactory {
    @Autowired
    private BankAccountSearcherI bankAccountSearcher;

    @Autowired
    private CategorySearcherI categorySearcher;

    public Operation create(String id, String bankAccountId, int sum, String categoryId) throws RuntimeException {
        Optional<BankAccount> bankAccount = bankAccountSearcher.findAccountById(bankAccountId);
        Optional<Category> category = categorySearcher.findCategoryById(categoryId);

        if (bankAccount.isEmpty() || category.isEmpty()) {
            throw new RuntimeException("Can't find bank accout of category per restored operation");
        }

        return new Operation(id, bankAccount.get(), sum, category.get());
    }

    public Operation create(String id, BankAccount bankAccount, int sum, Category category) {
        return new Operation(id, bankAccount, sum, category);
    }

    private Operation restore(OperationMemento operationMemento) throws RuntimeException {
        Optional<BankAccount> bankAccount = bankAccountSearcher.findAccountById(operationMemento.bankAccountId);
        Optional<Category> category = categorySearcher.findCategoryById(operationMemento.categoryId);

        if (bankAccount.isEmpty() || category.isEmpty()) {
            throw new RuntimeException("Can't find bank accout of category per restored operation");
        }

        return new Operation(operationMemento.id, bankAccount.get(), operationMemento.sum, category.get());
    }

    public Operation fromCsvString(String s) {
        String[] attrs = s.split(",");
        return restore(new OperationMemento(attrs[0], attrs[1], Integer.parseInt(attrs[2]),
                LocalDateTime.parse(attrs[3]), attrs[4], attrs[5]));
    }
}
