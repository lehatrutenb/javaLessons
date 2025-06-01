package hse.kpo.exporters;

import hse.kpo.domains.DurationReport;
import hse.kpo.domains.DurationReportElement;
import hse.kpo.interfaces.ExporterI;
import hse.kpo.interfaces.ReportI;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

@Component
public class YamlDurationReportExporter implements ExporterI<ReportI<DurationReportElement>> {
    @Override
    public void export(ReportI<DurationReportElement> data, Writer writer) throws IOException {
        writer.write("reports:\n");
        data.getReport().forEach(report -> {
            try {
                writer.write(String.format("    - %s\n", report.toString()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
