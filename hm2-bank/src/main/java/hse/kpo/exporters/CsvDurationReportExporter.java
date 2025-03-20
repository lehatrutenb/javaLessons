package hse.kpo.exporters;

import hse.kpo.domains.DurationReport;
import hse.kpo.domains.DurationReportElement;
import hse.kpo.interfaces.ExporterI;
import hse.kpo.interfaces.ReportI;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

@Component
public class CsvDurationReportExporter implements ExporterI<ReportI<DurationReportElement>> {
    @Override
    public void export(ReportI<DurationReportElement> data, Writer writer) throws IOException {
        writer.write("ReportMessage\n");
        data.getReport().forEach(report -> {
            try {
                writer.write(report.toString() + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
