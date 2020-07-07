package com.pdomingo.starter.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.pdomingo.kernel.core.vobjects.Address;

import java.io.IOException;

public class AddressDeserializer extends StdDeserializer<Address> {

    public static final AddressDeserializer INSTANCE = new AddressDeserializer();

    protected AddressDeserializer() {
        super(Address.class);
    }

    public Address deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        String addressStr = jsonParser.getValueAsString();
        String[] fields = addressStr.split(",");

        return Address.builder()
                .withStreet(fields[0])
                .withStreetNumber(fields[1])
                .withCity(fields[2])
                .withPostalCode(fields[3])
                .withCountry(fields[4])
                .build();
    }
}
