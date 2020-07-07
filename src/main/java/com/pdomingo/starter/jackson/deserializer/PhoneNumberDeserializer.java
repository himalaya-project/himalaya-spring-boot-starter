package com.pdomingo.starter.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.pdomingo.kernel.core.vobjects.PhoneNumber;

import java.io.IOException;

public class PhoneNumberDeserializer extends StdDeserializer<PhoneNumber> {
    public static final PhoneNumberDeserializer INSTANCE = new PhoneNumberDeserializer();

    protected PhoneNumberDeserializer() {
        super(PhoneNumber.class);
    }

    public PhoneNumber deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return PhoneNumber.valueOf(jsonParser.getValueAsString());
    }
}
