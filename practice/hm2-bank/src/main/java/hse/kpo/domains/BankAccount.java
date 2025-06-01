package hse.kpo.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import hse.kpo.interfaces.CsvExportable;
import lombok.Getter;
import lombok.Setter;


public class BankAccount implements CsvExportable {
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String name;

    @Getter
    private int balance;

    public BankAccount(String id, String name, int balance) {
        setId(id);
        setName(name);
        setBalance(balance);
    }

    public void setBalance(int value) throws RuntimeException {
        if (value < 0) {
            throw new RuntimeException("tried to set negative balance");
        }
        balance = value;
    }

    @Override
    public String csvHeader() {
        return "id,name,balance";
    }

    @Override
    public String toCsvString() {
        return String.format("%s,%s,%d", id, name, balance);
    }

    /*
    public void parse(String s) {
        String[] attrs = s.split(",");
        id = attrs[0];
        name = attrs[1];
        balance = Integer.parseInt(attrs[2]);
    }*/
}
