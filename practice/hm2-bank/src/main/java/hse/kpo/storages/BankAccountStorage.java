package hse.kpo.storages;

import hse.kpo.domains.BankAccount;
import hse.kpo.domains.BankAccountReport;
import hse.kpo.interfaces.BankAccountSearcherI;
import hse.kpo.interfaces.BankAccountStorageI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BankAccountStorage implements BankAccountStorageI, BankAccountSearcherI {
    private final BankAccountReport bankAccountReport = new BankAccountReport();

    @Override
    public Optional<BankAccount> findAccountById(String id) {
        return bankAccountReport.getReport().stream().filter(
                bankAccount -> bankAccount.getId().equals(id)).findFirst();
    }

    @Override
    public void add(BankAccount bankAccount) {
        bankAccountReport.addReportElement(bankAccount);
    }

    @Override
    public void addReport(BankAccountReport bankAccountReport) {
        bankAccountReport.getReport().forEach(this::add);
    }

    @Override
    public BankAccountReport getReport() {
        return bankAccountReport;
    }

}
