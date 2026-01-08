package com.raja.producer;

import com.raja.config.RabbitProperties;
import com.raja.dto.UserCreated;
import com.raja.dto.UserUpdated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.raja.util.RabbitEventType.USER_CREATED;
import static com.raja.util.RabbitEventType.USER_UPDATED;

@Service
@Slf4j
public class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitProperties rabbitProperties;

    public void sendUserCreatedMessage(UserCreated event) {
        send(USER_CREATED.key(), event);
    }

    public void sendUserUpdateMessage(UserUpdated event) {
        send(USER_UPDATED.key(), event);
    }

    private void send(String key, Object payload) {
        String exchange = rabbitProperties.getExchange().getName();
        String routingKey = rabbitProperties.getQueues().get(key).getRoutingKey();
        log.info("Sending message to exchange={}, routingKey={}, payload={}", exchange, routingKey, payload);
        rabbitTemplate.convertAndSend(exchange, routingKey, payload);
    }
}

