package hse.kpo.exporters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.domains.DurationReport;
import hse.kpo.domains.DurationReportElement;
import hse.kpo.interfaces.ExporterI;
import hse.kpo.interfaces.ReportI;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

@Component
public class JsonDurationReportExporter implements ExporterI<ReportI<DurationReportElement>> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void export(ReportI<DurationReportElement> data, Writer writer) throws IOException {
        objectMapper.writeValue(writer, data.getReport());
    }
}
