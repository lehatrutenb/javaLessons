package hse.kpo.importers.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import hse.kpo.domains.BankAccount;

import java.io.IOException;

public class BankAccountDeserializer extends StdDeserializer<BankAccount> {

    public BankAccountDeserializer() {
        this(null);
    }

    @Override
    public BankAccount deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = jp.getCodec().readTree(jp);
        return new BankAccount(node.get("id").toString(), node.get("name").toString(), node.get("createdBy").numberValue().intValue());
    }

    public BankAccountDeserializer(Class<?> vc) {
        super(vc);
    }
}
