package com.pdomingo.starter.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.pdomingo.kernel.core.vobjects.Address;

import java.io.IOException;

public class AddressSerializer extends StdSerializer<Address> {

    public static final AddressSerializer INSTANCE = new AddressSerializer();
    private static final String ADDRESS_FORMAT = "%s,%s,%s,%s,%s";

    public AddressSerializer() {
        super(Address.class);
    }

    public void serialize(Address address, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        String addressStr = String.format(ADDRESS_FORMAT,
                address.street(),
                address.streetNumber(),
                address.postalCode(),
                address.city(),
                address.country()
        );

        jsonGenerator.writeString(addressStr);

    }
}
