package com.pdomingo.starter.amqp;

import com.pdomingo.starter.amqp.service.AmqpRoutingConfiguration;
import com.pdomingo.starter.amqp.service.EventMapper;
import com.pdomingo.starter.amqp.service.EventService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(value = {
        // https://stackoverflow.com/a/31807548
        RabbitAutoConfiguration.class,
        KafkaAutoConfiguration.class
})
@ConditionalOnClass(AmqpTemplate.class)
public class HimalayaAmqpAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(EventService.class)
    @ConditionalOnBean(value = {
            AmqpTemplate.class,
            AmqpRoutingConfiguration.class
    })
    EventService eventService(AmqpTemplate amqpTemplate, AmqpRoutingConfiguration routingConfiguration, EventMapper eventMapper) {
        return new EventService(amqpTemplate, routingConfiguration, eventMapper);
    }

    @Bean
    MessageConverter messageConverter() {
        return new ProtobufMessageConverter();
    }
}
