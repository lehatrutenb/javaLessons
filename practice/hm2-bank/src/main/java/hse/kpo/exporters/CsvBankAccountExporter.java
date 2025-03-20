package hse.kpo.exporters;

import hse.kpo.domains.DurationReportElement;
import hse.kpo.interfaces.ExporterI;
import hse.kpo.interfaces.ReportI;

import java.io.IOException;
import java.io.Writer;

public class CsvBankAccountExporter {/*implements ExporterI<ReportI<DurationReportElement>> {
    @Override
    public void export(ReportI<DurationReportElement> data, Writer writer) throws IOException {
        writer.write("id,name,balance\n");
        data.getReport().forEach(account -> {
            try {
                writer.write(account.toCsvString() + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }*/
}
