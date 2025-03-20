package hse.kpo.exporters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hse.kpo.domains.BankAccount;
import hse.kpo.interfaces.ExporterI;
import hse.kpo.interfaces.ReportI;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

@Component
public class YamlObjectExporter<T>  implements ExporterI<ReportI<T>> {
    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
    @PostConstruct
    private void registerTime() {
        objectMapper.registerModule(new JavaTimeModule());
    }
    @Override
    public void export(ReportI<T> data, Writer writer) throws IOException {
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.writeValue(writer, data.getReport());
    }
}
