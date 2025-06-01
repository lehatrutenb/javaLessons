package hse.kpo.exporters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hse.kpo.domains.DurationReport;
import hse.kpo.domains.DurationReportElement;
import hse.kpo.interfaces.ExporterI;
import hse.kpo.interfaces.ReportI;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

@Component
public class JsonDurationReportExporter implements ExporterI<ReportI<DurationReportElement>> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void export(ReportI<DurationReportElement> data, Writer writer) throws IOException {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(writer, data.getReport());
    }
}
