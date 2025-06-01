package hse.kpo.factories;

import hse.kpo.domains.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class BankAccountFactory {
    public BankAccount create(String id, String name, int balance) {
        return new BankAccount(id, name, balance);
    }

    public BankAccount fromCsvString(String s) {
        String[] attrs = s.split(",");
        return new BankAccount(attrs[0], attrs[1], Integer.parseInt(attrs[2]));
    }
}
