package com.pdomingo.starter.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.pdoming.kernel.core.vobjects.Email;

import java.io.IOException;

public class EmailDeserializer extends StdDeserializer<Email> {
    public static final EmailDeserializer INSTANCE = new EmailDeserializer();

    protected EmailDeserializer() {
        super(Email.class);
    }

    public Email deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return Email.valueOf(jsonParser.getValueAsString());
    }
}
