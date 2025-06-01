package hse.kpo.factories;

import hse.kpo.domains.BankAccount;
import hse.kpo.domains.BankAccountReport;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BankAccountFactory {
    public BankAccount create(String id, String name, int balance) {
        return new BankAccount(id, name, balance);
    }

    public BankAccount fromCsvString(String s) {
        String[] attrs = s.split(",");
        return new BankAccount(attrs[0], attrs[1], Integer.parseInt(attrs[2]));
    }

    public BankAccount fromJsonMap(Map<String, Object> map) {
        return new BankAccount(map.get("id").toString(), map.get("name").toString(), Integer.parseInt(map.get("balance").toString()));
    }
}
