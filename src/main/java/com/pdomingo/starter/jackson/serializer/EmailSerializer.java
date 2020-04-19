package com.pdomingo.starter.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.pdoming.kernel.core.vobjects.Email;

import java.io.IOException;

public class EmailSerializer extends StdSerializer<Email> {

    public static final EmailSerializer INSTANCE = new EmailSerializer();

    public EmailSerializer() {
        super(Email.class);
    }

    public void serialize(Email email, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(email.toString());
    }
}
