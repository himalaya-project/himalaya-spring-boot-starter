package com.pdomingo.starter.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.pdomingo.kernel.core.ddd.DelegatedIdentifier;

import java.io.IOException;

public class DelegatedIdentifierSerializer extends StdSerializer<DelegatedIdentifier<?>> {

	public static final DelegatedIdentifierSerializer INSTANCE = new DelegatedIdentifierSerializer();

	public DelegatedIdentifierSerializer() {
		super(DelegatedIdentifier.class, false);
	}

	@Override
	public void serialize(DelegatedIdentifier<?> id, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeString(id.toString());
	}
}
