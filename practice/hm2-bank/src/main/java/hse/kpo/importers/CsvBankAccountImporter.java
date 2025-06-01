package hse.kpo.importers;

import hse.kpo.domains.BankAccount;
import hse.kpo.domains.BankAccountReport;
import hse.kpo.factories.BankAccountFactory;
import hse.kpo.interfaces.BankAccountStorageI;
import hse.kpo.interfaces.CsvObjectImporterI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

@Component
public class CsvBankAccountImporter implements CsvObjectImporterI<BankAccountReport> {
    @Autowired
    private BankAccountFactory bankAccountFactory;
    @Override
    public BankAccountReport parse(BufferedReader reader) throws IOException {
        reader.readLine();
        return new BankAccountReport(reader.lines().map(
                line -> bankAccountFactory.fromCsvString(line)).toList());
    }
}
