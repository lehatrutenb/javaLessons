package hse.kpo.interfaces;

import hse.kpo.domains.BankAccount;
import hse.kpo.domains.BankAccountReport;

import java.util.Optional;

public interface BankAccountStorageI {
    public void add(BankAccount bankAccount);
    public void addReport(BankAccountReport bankAccountReport);
    public BankAccountReport getReport();
}
