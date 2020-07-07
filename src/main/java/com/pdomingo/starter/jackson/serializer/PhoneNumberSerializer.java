package com.pdomingo.starter.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.pdomingo.kernel.core.vobjects.PhoneNumber;

import java.io.IOException;

public class PhoneNumberSerializer extends StdSerializer<PhoneNumber> {

    public static final PhoneNumberSerializer INSTANCE = new PhoneNumberSerializer();

    public PhoneNumberSerializer() {
        super(PhoneNumber.class);
    }

    public void serialize(PhoneNumber phoneNumber, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(phoneNumber.rawPhoneNumber());
    }
}
