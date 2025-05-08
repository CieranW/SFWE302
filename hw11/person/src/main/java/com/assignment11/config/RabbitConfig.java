package com.assignment11.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.*;

@Configuration
public class RabbitConfig {
    public static final String PERSON_EXCHANGE     = "person.exchange";
    public static final String PERSON_QUEUE        = "person.queue";
    public static final String PERSON_ROUTING_KEY  = "person.routing";
    public static final String PERSON_ERROR_QUEUE  = "person.error.queue";

    @Bean Queue personQueue() {
        return QueueBuilder.durable(PERSON_QUEUE).build();
    }

    @Bean Queue errorQueue() {
        return QueueBuilder.durable(PERSON_ERROR_QUEUE).build();
    }

    @Bean DirectExchange exchange() {
        return new DirectExchange(PERSON_EXCHANGE);
    }

    @Bean Binding bindingPerson(Queue personQueue, DirectExchange exchange) {
        return BindingBuilder.bind(personQueue)
                             .to(exchange)
                             .with(PERSON_ROUTING_KEY);
    }

    @Bean Binding bindingError(Queue errorQueue, DirectExchange exchange) {
        return BindingBuilder.bind(errorQueue)
                             .to(exchange)
                             .with(PERSON_ROUTING_KEY + ".error");
    }

    @Bean public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean public RabbitTemplate rabbitTemplate(ConnectionFactory cf) {
        RabbitTemplate tpl = new RabbitTemplate(cf);
        tpl.setMessageConverter(converter());
        return tpl;
    }

    @Bean public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
        ConnectionFactory cf
    ) {
        var factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(cf);
        factory.setMessageConverter(converter());
        return factory;
    }
}
