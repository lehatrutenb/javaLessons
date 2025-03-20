package hse.kpo.importers;

import hse.kpo.domains.CategoryReport;
import hse.kpo.domains.Operation;
import hse.kpo.domains.OperationMementoReport;
import hse.kpo.domains.OperationReport;
import hse.kpo.factories.CategoryFactory;
import hse.kpo.factories.OperationFactory;
import hse.kpo.interfaces.CsvObjectImporterI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;

@Component
public class CsvOperationImporter implements CsvObjectImporterI<OperationReport> {
    @Autowired
    private OperationFactory operationFactory;
    @Override
    public OperationReport parse(BufferedReader reader) {
        return new OperationReport(reader.lines().map(
                line -> operationFactory.fromCsvString(line)).toList());
    }
}
