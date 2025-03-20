package hse.kpo.facade;

import hse.kpo.domains.BankAccount;
import hse.kpo.domains.Category;
import hse.kpo.domains.OperationMemento;
import hse.kpo.domains.OperationMementoReport;
import hse.kpo.enums.DataType;
import hse.kpo.exporters.JsonObjectExporter;
import hse.kpo.exporters.YamlObjectExporter;
import hse.kpo.factories.BankAccountFactory;
import hse.kpo.factories.CategoryFactory;
//import hse.kpo.factories.DurationReportExporterFactory;
import hse.kpo.factories.OperationFactory;
import hse.kpo.interfaces.BankAccountStorageI;
import hse.kpo.interfaces.CategoryStorageI;
import hse.kpo.interfaces.ExporterVisitorI;
import hse.kpo.interfaces.OperationStorageI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class BankExporterFacade {
    private final BankAccountStorageI bankAccountStorage;
    private final OperationStorageI operationStorage;
    private final CategoryStorageI categoryStorage;
    private final ExporterVisitorI exporterVisitor;
    private final YamlObjectExporter<Category> yamlObjectExporterCategory;
    private final YamlObjectExporter<BankAccount> yamlObjectExporterBankAccount;
    private final YamlObjectExporter<OperationMemento> yamlObjectExporterOperationMemento;
    private final JsonObjectExporter<Category> jsonObjectExporterCategory;
    private final JsonObjectExporter<BankAccount> jsonObjectExporterBankAccount;
    private final JsonObjectExporter<OperationMemento> jsonObjectExporterOperationMemento;
    public void exportAll(DataType dataType) throws Throwable {
        switch (dataType) {
            case CSV -> exportCsv();
            case JSON -> exportJson();
            case YAML -> exportYaml();
            default -> throw new IllegalArgumentException("Unsupported format: " + dataType);
        }
    }

    private void exportCsv() throws Throwable {
        try (FileWriter fileWriter = new FileWriter("BankAccounts")) {
            exporterVisitor.setWriter(fileWriter);
            bankAccountStorage.getReport().visit(exporterVisitor);
        }
        try (FileWriter fileWriter = new FileWriter("Categories")) {
            exporterVisitor.setWriter(fileWriter);
            categoryStorage.getReport().visit(exporterVisitor);
        }
        try (FileWriter fileWriter = new FileWriter("Operations")) {
            exporterVisitor.setWriter(fileWriter);
            new OperationMementoReport(operationStorage.getReport()).visit(exporterVisitor);
        }
    }

    private void exportJson() throws Throwable {
        try (FileWriter fileWriter = new FileWriter("BankAccounts")) {
            jsonObjectExporterBankAccount.export(bankAccountStorage.getReport(), fileWriter);
        }
        try (FileWriter fileWriter = new FileWriter("Categories")) {
            jsonObjectExporterCategory.export(categoryStorage.getReport(), fileWriter);
        }
        try (FileWriter fileWriter = new FileWriter("Operations")) {
            jsonObjectExporterOperationMemento.export(new OperationMementoReport(operationStorage.getReport()), fileWriter);
        }
    }

    private void exportYaml() throws Throwable {
        try (FileWriter fileWriter = new FileWriter("BankAccounts")) {
            yamlObjectExporterBankAccount.export(bankAccountStorage.getReport(), fileWriter);
        }
        try (FileWriter fileWriter = new FileWriter("Categories")) {
            yamlObjectExporterCategory.export(categoryStorage.getReport(), fileWriter);
        }
        try (FileWriter fileWriter = new FileWriter("Operations")) {
            yamlObjectExporterOperationMemento.export(new OperationMementoReport(operationStorage.getReport()), fileWriter);
        }
    }
}
