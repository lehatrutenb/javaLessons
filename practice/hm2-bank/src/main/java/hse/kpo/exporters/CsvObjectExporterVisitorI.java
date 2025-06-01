package hse.kpo.exporters;

import hse.kpo.domains.*;
import hse.kpo.interfaces.ExporterVisitorI;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

@Component
public class CsvObjectExporterVisitorI implements ExporterVisitorI {
    private Writer writer;

    @Override
    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void runOnBankAccount(BankAccountReport bankAccountReport) throws IOException {
        if (bankAccountReport.getReport().isEmpty()) {
            return;
        }
        writer.write(bankAccountReport.getReport().getFirst().csvHeader() + "\n");
        bankAccountReport.getReport().forEach(bankAccount -> {
            try {
                writer.write(bankAccount.toCsvString() + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void runOnCategory(CategoryReport categoryReport) throws IOException {
        if (categoryReport.getReport().isEmpty()) {
            return;
        }
        writer.write(categoryReport.getReport().getFirst().csvHeader() + "\n");
        categoryReport.getReport().forEach(category -> {
            try {
                writer.write(category.toCsvString() + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void runOnOperationMemento(OperationMementoReport operationMementoReport) throws IOException {
        if (operationMementoReport.getReport().isEmpty()) {
            return;
        }
        writer.write(operationMementoReport.getReport().getFirst().csvHeader() + "\n");
        operationMementoReport.getReport().forEach(operationMemento -> {
            try {
                writer.write(operationMemento.toCsvString() + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
