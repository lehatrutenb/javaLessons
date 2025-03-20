package hse.kpo.importers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import hse.kpo.domains.BankAccount;
import hse.kpo.domains.BankAccountReport;
import hse.kpo.importers.deserializers.BankAccountDeserializer;
import hse.kpo.interfaces.ReportI;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

@Component
public class JsonObjectImporter {
    private final ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
    @PostConstruct
    public void findModules() {
        objectMapper.findAndRegisterModules();
    }
    public List<Map<String, Object>> parse(Reader reader) throws IOException {
        return objectMapper.readValue(reader, new TypeReference<List<Map<String, Object>>>(){});
    }
}
