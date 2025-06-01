package hse.kpo.domains;

import hse.kpo.interfaces.BankVisitorI;
import hse.kpo.interfaces.ReportI;
import hse.kpo.interfaces.Visitable;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BankAccountReport implements ReportI<BankAccount>, Visitable {
    private List<BankAccount> bankAccounts = new ArrayList<>();
    @Override
    public void addReportElement(BankAccount elem) {
        bankAccounts.add(elem);
    }

    @Override
    public List<BankAccount> getReport() {
        return bankAccounts;
    }

    public BankAccountReport() {
        bankAccounts = new ArrayList<>();
    }

    public BankAccountReport(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    @Override
    public void visit(BankVisitorI bankVisitor) throws IOException {
        bankVisitor.runOnBankAccount(this);
    }
}
