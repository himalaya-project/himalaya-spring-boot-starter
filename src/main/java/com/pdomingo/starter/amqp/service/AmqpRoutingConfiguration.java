package com.pdomingo.starter.amqp.service;

import com.pdoming.kernel.core.ddd.DomainEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class AmqpRoutingConfiguration {

    private final Map<Class<? extends DomainEvent>, Route> routingMap;

    public AmqpRoutingConfiguration(Map<Class<? extends DomainEvent>, Route> routingMap) {
        this.routingMap = Objects.requireNonNull(routingMap);
    }

    public Optional<Route> findRouteFor(Class<? extends DomainEvent> eventClass) {
        return Optional.ofNullable(routingMap.get(eventClass));
    }

    public static AmqpRoutingConfiguration.Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final Map<Class<? extends DomainEvent>, Route> routingMap;

        public Builder() {
            this.routingMap = new HashMap<>();
        }

        public Builder newRoute(
                Class<? extends DomainEvent> clazz,
                Route route
        ) {
            this.routingMap.put(clazz, route);
            return this;
        }

        public AmqpRoutingConfiguration build() {
            return new AmqpRoutingConfiguration(routingMap);
        }
    }
}
