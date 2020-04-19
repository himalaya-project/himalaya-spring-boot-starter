package com.pdomingo.starter.jackson.module;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.pdoming.kernel.core.vobjects.Address;
import com.pdoming.kernel.core.vobjects.Email;
import com.pdoming.kernel.core.vobjects.PhoneNumber;
import com.pdomingo.starter.jackson.deserializer.AddressDeserializer;
import com.pdomingo.starter.jackson.deserializer.EmailDeserializer;
import com.pdomingo.starter.jackson.deserializer.PhoneNumberDeserializer;
import com.pdomingo.starter.jackson.serializer.AddressSerializer;
import com.pdomingo.starter.jackson.serializer.DelegatedIdentifierSerializer;
import com.pdomingo.starter.jackson.serializer.EmailSerializer;
import com.pdomingo.starter.jackson.serializer.PhoneNumberSerializer;

public class HimalayaModule extends SimpleModule {

    public HimalayaModule() {
        addSerializer(DelegatedIdentifierSerializer.INSTANCE);

        addSerializer(Email.class, EmailSerializer.INSTANCE);
        addSerializer(Address.class, AddressSerializer.INSTANCE);
        addSerializer(PhoneNumber.class, PhoneNumberSerializer.INSTANCE);

        addDeserializer(Email.class, EmailDeserializer.INSTANCE);
        addDeserializer(Address.class, AddressDeserializer.INSTANCE);
        addDeserializer(PhoneNumber.class, PhoneNumberDeserializer.INSTANCE);
    }
}
