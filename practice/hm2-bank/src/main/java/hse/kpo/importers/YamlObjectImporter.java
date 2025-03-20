package hse.kpo.importers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;

@Component
public class YamlObjectImporter<T> {
    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    @PostConstruct
    public void findModules() {
        objectMapper.findAndRegisterModules();
    }
    public T parse(Reader reader, Class curClass) throws IOException {
        return (T) objectMapper.readValue(reader, curClass);
    }
}
