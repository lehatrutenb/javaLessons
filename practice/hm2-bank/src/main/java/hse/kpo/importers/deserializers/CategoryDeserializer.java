package hse.kpo.importers.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hse.kpo.domains.BankAccount;
import hse.kpo.domains.Category;
import hse.kpo.domains.Operation;
import hse.kpo.enums.OperationType;

import java.io.IOException;

public class CategoryDeserializer extends StdDeserializer<Category> {

    public CategoryDeserializer() {
        this(null);
    }

    @Override
    public Category deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = jp.getCodec().readTree(jp);
        return new Category(OperationType.valueOf(node.get("operationType").toString()),node.get("id").toString(), node.get("name").toString());
    }

    public CategoryDeserializer(Class<?> vc) {
        super(vc);
    }
}
