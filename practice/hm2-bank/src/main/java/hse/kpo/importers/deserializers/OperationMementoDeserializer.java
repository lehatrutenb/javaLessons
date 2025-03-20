package hse.kpo.importers.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hse.kpo.domains.Category;
import hse.kpo.domains.OperationMemento;
import hse.kpo.enums.OperationType;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class OperationMementoDeserializer extends StdDeserializer<OperationMemento> {

    public OperationMementoDeserializer() {
        this(null);
    }

    @Override
    public OperationMemento deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = jp.getCodec().readTree(jp);
        return new OperationMemento(node.get("id").toString(), node.get("bankAccountId").toString(),
                node.get("sum").numberValue().intValue(),
                LocalDateTime.parse(node.get("timestamp").toString(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")), node.get("description").toString(),
                node.get("categoryId").toString());
    }

    public OperationMementoDeserializer(Class<?> vc) {
        super(vc);
    }
}
