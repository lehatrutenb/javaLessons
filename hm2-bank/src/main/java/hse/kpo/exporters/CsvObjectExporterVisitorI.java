package hse.kpo.exporters;

import hse.kpo.domains.*;
import hse.kpo.interfaces.ExporterVisitorI;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

@Component
public class CsvObjectExporterVisitorI implements ExporterVisitorI {
    /*@Override
    public void export(ReportI<CsvConvertable> data, Writer writer) throws IOException {
        if (data.getReport().isEmpty()) {
            writer.write((new BankAccount("1","1",1)).csvHeader() + "\n");
            return;
        }
        writer.write(data.getReport().getFirst().csvHeader() + "\n");
        data.getReport().forEach(element -> {
            try {
                writer.write(element.toCsvString() + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }*/
    /*
    @Override
    public String csvHeader() {
        return "id,name,balance";
    }

    @Override
    public String toCsvString() {
        return String.format("%s,%s,%d", id, name, balance);
    }

    @Override
    public void parse(String s) {
        String[] attrs = s.split(",");
        id = attrs[0];
        name = attrs[1];
        balance = Integer.parseInt(attrs[2]);
    }
     */
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
