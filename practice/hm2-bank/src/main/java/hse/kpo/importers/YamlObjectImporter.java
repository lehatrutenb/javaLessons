package hse.kpo.importers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

@Component
public class YamlObjectImporter {
    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    @PostConstruct
    public void findModules() {
        objectMapper.findAndRegisterModules();
    }
    public List<Map<String, Object>> parse(Reader reader) throws IOException {
        return objectMapper.readValue(reader, new TypeReference<List<Map<String, Object>>>(){});
    }
}
