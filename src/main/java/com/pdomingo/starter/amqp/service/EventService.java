package com.pdomingo.starter.amqp.service;

import com.pdomingo.kernel.core.ddd.DomainEvent;
import com.pdomingo.kernel.core.ddd.Identifiable;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.NoSuchElementException;
import java.util.Objects;

public class EventService {

    private final AmqpTemplate amqpTemplate;
    private final AmqpRoutingConfiguration routingConfiguration;
    private final EventMapper eventMapper;

    public EventService(AmqpTemplate amqpTemplate, AmqpRoutingConfiguration routingConfiguration, EventMapper eventMapper) {
        this.amqpTemplate = Objects.requireNonNull(amqpTemplate);
        this.routingConfiguration = Objects.requireNonNull(routingConfiguration);
        this.eventMapper = Objects.requireNonNull(eventMapper);
    }

    public EventService(AmqpTemplate amqpTemplate, AmqpRoutingConfiguration routingConfiguration) {
        this(amqpTemplate, routingConfiguration, EventMapper.IDENTITY);
    }

    public void send(DomainEvent<? extends Identifiable> event) {
        Route route = routingConfiguration.findRouteFor(event.getClass())
                .orElseThrow(NoSuchElementException::new);
        Object result = eventMapper.map(event);
        amqpTemplate.convertAndSend(route.exchange(), route.key(), result);
    }

    public AmqpTemplate amqpTemplate() {
        return amqpTemplate;
    }

    public AmqpRoutingConfiguration routingConfiguration() {
        return routingConfiguration;
    }
}
