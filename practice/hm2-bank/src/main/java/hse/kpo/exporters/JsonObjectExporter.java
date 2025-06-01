package hse.kpo.exporters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hse.kpo.domains.BankAccount;
import hse.kpo.interfaces.ExporterI;
import hse.kpo.interfaces.ReportI;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

@Component
public class JsonObjectExporter<T>  implements ExporterI<ReportI<T>> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @PostConstruct
    private void registerTime() {
        objectMapper.registerModule(new JavaTimeModule());
    }
    @Override
    public void export(ReportI<T> data, Writer writer) throws IOException {
        objectMapper.writeValue(writer, data.getReport());
    }
}