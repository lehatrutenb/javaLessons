package hse.kpo.services;

import hse.kpo.domains.*;
import hse.kpo.dtos.OperationsGrouppedByCategories;
import hse.kpo.interfaces.BankAccountStorageI;
import hse.kpo.interfaces.CategoryStorageI;
import hse.kpo.interfaces.OperationStorageI;
import hse.kpo.storages.BankAccountStorage;
import hse.kpo.storages.OperationStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class BankService {
    @Autowired
    private OperationStorageI operationStorage;
    @Autowired
    private BankAccountStorageI bankAccountStorage;
    @Autowired
    private CategoryStorageI categoryStorage;
    public int calcOperationSumChangeInPeriod(String bankAccountId, LocalDateTime start, LocalDateTime end) {
        return operationStorage.getReport().getReport().stream()
                .filter(operation -> operation.getId().equals(bankAccountId))
                .filter(operation -> operation.getTimestamp().isAfter(start) && operation.getTimestamp().isBefore(end))
                .reduce(
                0, (sum, operation) -> sum + operation.getSum(), Integer::sum
                );
    }

    public OperationsGrouppedByCategories getOperationsGrouppedByCategories(String bankAccountId) {
        OperationsGrouppedByCategories res = new OperationsGrouppedByCategories();
        operationStorage.getReport().getReport().stream()
                .filter(operation -> operation.getId().equals(bankAccountId))
                .forEach(operation -> res.add(List.of(operation), operation.getCategory()));
        return res;
    }

    public List<OperationMemento> getOperationsByAccountId(String id) {
        return new OperationMementoReport(operationStorage.getReport()).getReport().stream().filter(operation -> operation.bankAccountId.equals(id)).toList();
    }
    public BankAccountReport getBankAccounts() {
        return bankAccountStorage.getReport();
    }
    public CategoryReport getCategories() {
        return categoryStorage.getReport();
    }
}
