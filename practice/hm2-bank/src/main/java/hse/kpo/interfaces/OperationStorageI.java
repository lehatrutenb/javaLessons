package hse.kpo.interfaces;

import hse.kpo.domains.BankAccountReport;
import hse.kpo.domains.Operation;
import hse.kpo.domains.OperationReport;

public interface OperationStorageI {
    public void add(Operation operation);
    public void addReport(OperationReport operationReport);
    public OperationReport getReport();
}
