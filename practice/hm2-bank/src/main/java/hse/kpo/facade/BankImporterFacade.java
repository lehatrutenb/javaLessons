package hse.kpo.facade;

import hse.kpo.domains.*;
import hse.kpo.enums.DataType;
import hse.kpo.exporters.JsonBankAccountExporter;
import hse.kpo.exporters.JsonObjectExporter;
import hse.kpo.exporters.YamlObjectExporter;
import hse.kpo.factories.BankAccountFactory;
import hse.kpo.factories.CategoryFactory;
//import hse.kpo.factories.DurationReportExporterFactory;
import hse.kpo.factories.OperationFactory;
import hse.kpo.importers.*;
import hse.kpo.interfaces.BankAccountStorageI;
import hse.kpo.interfaces.CategoryStorageI;
import hse.kpo.interfaces.ExporterVisitorI;
import hse.kpo.interfaces.OperationStorageI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class BankImporterFacade {
    private final BankAccountStorageI bankAccountStorage;
    private final OperationStorageI operationStorage;
    private final CategoryStorageI categoryStorage;
    private final ExporterVisitorI exporterVisitor;

    private final JsonObjectImporter<BankAccountReport> bankAccountJsonObjectImporter;
    private final JsonObjectImporter<CategoryReport> categoryJsonObjectImporter;
    private final JsonObjectImporter<OperationReport> operationJsonObjectImporter;

    private final YamlObjectImporter<BankAccountReport> bankAccountYamlObjectImporter;
    private final YamlObjectImporter<CategoryReport> categoryYamlObjectImporter;
    private final YamlObjectImporter<OperationReport> operationYamlObjectImporter;

    private final CsvBankAccountImporter bankAccountCsvImporter;
    private final CsvCategoryImporter categoryCsvImporter;
    private final CsvOperationImporter operationCsvImporter;

    public void importAll(DataType dataType) throws Throwable {
        switch (dataType) {
            case CSV -> importCsv();
            case JSON -> importJson();
            case YAML -> importYaml();
            default -> throw new IllegalArgumentException("Unsupported format: " + dataType);
        }
    }

    private void importCsv() throws Throwable {
        try (FileReader fileReader = new FileReader("BankAccounts")) {
            bankAccountStorage.addReport(bankAccountCsvImporter.parse(new BufferedReader(fileReader)));
        }
        try (FileReader fileReader = new FileReader("Categories")) {
            categoryStorage.addReport(categoryCsvImporter.parse(new BufferedReader(fileReader)));
        }
        try (FileReader fileReader = new FileReader("Operations")) {
            operationStorage.addReport(operationCsvImporter.parse(new BufferedReader(fileReader)));
        }
    }

    private void importJson() throws Throwable {
        try (FileReader fileReader = new FileReader("BankAccounts")) {
            bankAccountStorage.addReport(bankAccountJsonObjectImporter.parse(new BufferedReader(fileReader), BankAccountReport.class));
        }
        try (FileReader fileReader = new FileReader("Categories")) {
            categoryStorage.addReport(categoryJsonObjectImporter.parse(new BufferedReader(fileReader), CategoryReport.class));
        }
        try (FileReader fileReader = new FileReader("Operations")) {
            operationStorage.addReport(operationJsonObjectImporter.parse(new BufferedReader(fileReader), OperationMementoReport.class));
        }
    }

    private void importYaml() throws Throwable {
        try (FileReader fileReader = new FileReader("BankAccounts")) {
            bankAccountStorage.addReport(bankAccountYamlObjectImporter.parse(new BufferedReader(fileReader), BankAccountReport.class));
        }
        try (FileReader fileReader = new FileReader("Categories")) {
            categoryStorage.addReport(categoryYamlObjectImporter.parse(new BufferedReader(fileReader), CategoryReport.class));
        }
        try (FileReader fileReader = new FileReader("Operations")) {
            operationStorage.addReport(operationYamlObjectImporter.parse(new BufferedReader(fileReader), OperationMementoReport.class));
        }
    }
}
