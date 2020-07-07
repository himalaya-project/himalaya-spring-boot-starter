package com.pdomingo.starter.amqp.service;

import com.pdomingo.kernel.core.ddd.DomainEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class EventMapper {

    public static final EventMapper IDENTITY = new EventMapper() {
        @Override
        public Object map(DomainEvent event) {
            return event;
        }
    };

    private final Map<Class<? extends DomainEvent>, Function<? extends DomainEvent, ?>> mappings;

    protected EventMapper() {
        this.mappings = new HashMap<>();
    }

    protected <T extends DomainEvent, K> void register(Class<T> clazz, Function<T, K> map) {
        mappings.put(clazz, map);
    }

    public Object map(DomainEvent event) {
        Function<DomainEvent, Object> function = (Function<DomainEvent, Object>) mappings.get(event.getClass());
        return function.apply(event);
    }
}
