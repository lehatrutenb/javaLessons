package hse.kpo.interfaces;

import java.io.IOException;

import hse.kpo.domains.BankAccountReport;
import hse.kpo.domains.CategoryReport;
import hse.kpo.domains.OperationMementoReport;

public interface BankVisitorI {
    public void runOnBankAccount(BankAccountReport bankAccountReport) throws IOException;
    public void runOnCategory(CategoryReport categoryReport) throws IOException;
    public void runOnOperationMemento(OperationMementoReport operationMementoReport) throws IOException;
}
