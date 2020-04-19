package com.pdomingo.starter.amqp.service;

import com.pdoming.kernel.core.ddd.DomainEvent;
import com.pdoming.kernel.core.ddd.Identifiable;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.NoSuchElementException;
import java.util.Objects;

public class EventService {

    private final AmqpTemplate amqpTemplate;
    private final AmqpRoutingConfiguration routingConfiguration;

    public EventService(AmqpTemplate amqpTemplate, AmqpRoutingConfiguration routingConfiguration) {
        this.amqpTemplate = Objects.requireNonNull(amqpTemplate);
        this.routingConfiguration = Objects.requireNonNull(routingConfiguration);
    }

    public void send(DomainEvent<? extends Identifiable> event) {
        Route route = routingConfiguration.findRouteFor(event.getClass())
                .orElseThrow(NoSuchElementException::new);
        amqpTemplate.convertAndSend(route.exchange(), route.key(), event);
    }

    public AmqpTemplate amqpTemplate() {
        return amqpTemplate;
    }

    public AmqpRoutingConfiguration routingConfiguration() {
        return routingConfiguration;
    }
}
