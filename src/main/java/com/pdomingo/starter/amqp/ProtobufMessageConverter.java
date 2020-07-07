package com.pdomingo.starter.amqp;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

public class ProtobufMessageConverter extends AbstractMessageConverter {

    private final static String MESSAGE_TYPE_NAME = "_msg_type_name_";
    private final static String CONTENT_TYPE_PROTOBUF = "application/x-protobuf";

    private static final Map<Class<?>, com.google.protobuf.Message> builderCache = new ConcurrentReferenceHashMap<>();

    public ProtobufMessageConverter() { }

    @Override
    protected Message createMessage(Object object, MessageProperties messageProperties) {
        Objects.requireNonNull(object, "Object to send is null");

        if (!com.google.protobuf.Message.class.isAssignableFrom(object.getClass())) {
            throw new MessageConversionException("Message wasn't a protobuf. Actual type is " + object.getClass().getName());
        } else {
            com.google.protobuf.Message protobuf = (com.google.protobuf.Message) object;
            byte[] byteArray = protobuf.toByteArray();

            messageProperties.setContentLength(byteArray.length);
            messageProperties.setContentType(ProtobufMessageConverter.CONTENT_TYPE_PROTOBUF);
            messageProperties.setHeader(ProtobufMessageConverter.MESSAGE_TYPE_NAME, protobuf.getDescriptorForType().getFullName());

            return new Message(byteArray, messageProperties);
        }
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {

        com.google.protobuf.Message parsedMessage = null;
        Type argumentType = message.getMessageProperties().getInferredArgumentType();

        if (argumentType instanceof Class clazz &&
            com.google.protobuf.Message.class.isAssignableFrom(clazz))
        {
            try {
                parsedMessage = getDefaultInstance(clazz)
                        .newBuilderForType()
                        .mergeFrom(message.getBody())
                        .build();
            } catch (Exception e) {
                throw new AmqpRejectAndDontRequeueException("Cannot convert message. Unknown message type " + clazz.getName(), e);
            }
        }
        return parsedMessage;
    }

    private com.google.protobuf.Message getDefaultInstance(Class<Message> clazz) {
        try {
            com.google.protobuf.Message message = builderCache.get(clazz);
            if (message == null) {
                Method method = clazz.getMethod("getDefaultInstanceForType");
                message = (com.google.protobuf.Message) method.invoke(clazz);
                builderCache.put(clazz, message);
            }
            return message;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Invalid Protobuf Message type: no invocable getDefaultInstance() method on " + clazz.getName(), ex);
        }
    }
}
