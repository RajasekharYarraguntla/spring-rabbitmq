package com.raja.consumer;


import com.raja.dto.UserCreated;
import com.raja.dto.UserUpdated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQConsumer {

    @RabbitListener(queues = "${rabbitmq.queues.user-created.queue-name}")
    public void consumeUserCreated(UserCreated userCreated) {
        log.info("User Created Message: " + userCreated);
    }

    @RabbitListener(queues = "${rabbitmq.queues.user-updated.queue-name}")
    public void consumeUserUpdated(UserUpdated userUpdated) {
        log.info("User Updated Message:" + userUpdated);
    }
}
