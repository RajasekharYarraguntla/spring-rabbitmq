package com.raja.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RabbitOrderQueueConfig {

    @Autowired
    private RabbitProperties properties;

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(properties.getExchange().getName());
    }

    @Bean
    public Declarables queuesAndBindings() {
        List<Declarable> declarable = new ArrayList<>();
        properties.getQueues().values().forEach(q -> {
            Queue queue = new Queue(q.getQueueName(), true);
            Binding binding = BindingBuilder.bind(queue).to(orderExchange()).with(q.getRoutingKey());
            declarable.add(queue);
            declarable.add(binding);
        });
        return new Declarables(declarable);
    }

}
