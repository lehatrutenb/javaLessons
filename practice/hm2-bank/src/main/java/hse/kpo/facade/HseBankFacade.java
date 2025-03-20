package hse.kpo.facade;

import hse.kpo.domains.BankAccount;
import hse.kpo.domains.Category;
import hse.kpo.domains.OperationMemento;
import hse.kpo.domains.OperationMementoReport;
import hse.kpo.dtos.OperationsGrouppedByCategories;
import hse.kpo.enums.DataType;
import hse.kpo.enums.OperationType;
import hse.kpo.factories.BankAccountFactory;
import hse.kpo.factories.CategoryFactory;
//import hse.kpo.factories.DurationReportExporterFactory;
import hse.kpo.factories.OperationFactory;
import hse.kpo.interfaces.*;
import hse.kpo.services.BankService;
import hse.kpo.storages.BankAccountStorage;
import hse.kpo.storages.CategoryStorage;
import hse.kpo.storages.OperationStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class HseBankFacade {
    private final BankAccountStorageI bankAccountStorage;
    private final OperationStorageI operationStorage;
    private final CategoryStorageI categoryStorage;
    private final BankAccountFactory bankAccountFactory;
    private final CategoryFactory categoryFactory;
    //private final DurationReportExporterFactory durationReportExporterFactory;
    private final OperationFactory operationFactory;
    private final ExporterVisitorI exporterVisitor;
    private final BankExporterFacade bankExporterFacade;
    private final BankImporterFacade bankImporterFacade;
    private final BankService bankService;

    public void exportAll(DataType dataType) throws Throwable {
        bankExporterFacade.exportAll(dataType);
    }

    public void importAll(DataType dataType) throws Throwable {
        bankImporterFacade.importAll(dataType);
    }

    public void addBankAccount(String id, String name, int balance) {
        bankAccountStorage.add(bankAccountFactory.create(id, name, balance));
    }
    public void addCategory(OperationType operationType, String id, String name) {
        categoryStorage.add(categoryFactory.create(operationType, id, name));
    }
    public void addOperation(String id, String bankAccountId, int sum, String categoryId) {
        operationStorage.add(operationFactory.create(id, bankAccountId, sum, categoryId));
    }

    public int calcOperationSumChangeInPeriod(String bankAccountId, LocalDateTime start, LocalDateTime end) {
        return bankService.calcOperationSumChangeInPeriod(bankAccountId, start, end);
    }

    public OperationsGrouppedByCategories getOperationsGrouppedByCategories(String bankAccountId) {
        return bankService.getOperationsGrouppedByCategories(bankAccountId);
    }

    public List<OperationMemento> getOperationsByAccountId(String id) {
        return bankService.getOperationsByAccountId(id);
    }
    public List<BankAccount> getBankAccounts() {
        return bankAccountStorage.getReport().getReport();
    }
    public List<Category> getCategories() {
        return categoryStorage.getReport().getReport();
    }
}
