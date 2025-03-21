package hse.kpo.importers;

import hse.kpo.domains.BankAccountReport;
import hse.kpo.domains.CategoryReport;
import hse.kpo.factories.BankAccountFactory;
import hse.kpo.factories.CategoryFactory;
import hse.kpo.interfaces.CsvObjectImporterI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

@Component
public class CsvCategoryImporter implements CsvObjectImporterI<CategoryReport> {
    @Autowired
    private CategoryFactory categoryFactory;
    @Override
    public CategoryReport parse(BufferedReader reader) throws IOException {
        reader.readLine();
        return new CategoryReport(reader.lines().map(
                line -> categoryFactory.fromCsvString(line)).toList());
    }
}
