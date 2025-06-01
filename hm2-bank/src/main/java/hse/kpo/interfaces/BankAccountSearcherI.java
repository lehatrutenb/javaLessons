package hse.kpo.interfaces;

import hse.kpo.domains.BankAccount;

import java.util.Optional;

public interface BankAccountSearcherI {
    public Optional<BankAccount> findAccountById(String id);
}
