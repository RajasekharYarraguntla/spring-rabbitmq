package com.raja.consumer;


import com.rabbitmq.client.Channel;
import com.raja.dto.UserCreated;
import com.raja.dto.UserUpdated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class RabbitMQConsumer {

    @RabbitListener(queues = "${rabbitmq.queues.user-created.queue-name}", ackMode = "MANUAL")
    public void consumeUserCreated(UserCreated userCreated, Message message, Channel channel) throws Exception {

        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            System.out.println("Received message: " + userCreated);
            processOrder(userCreated);
            channel.basicAck(deliveryTag, false);
        } catch (Exception ex) {
            channel.basicNack(deliveryTag, false, false);
            log.error("Error processing message: {}", ex.getMessage());
            throw ex;
        }
    }
    private void processOrder(UserCreated userCreated) {
        log.info("User Created  Message:{}", userCreated);
    }

    @RabbitListener(queues = "${rabbitmq.queues.user-updated.queue-name}")
    public void consumeUserUpdated(UserUpdated userUpdated) {
        log.info("User Updated Message:{}", userUpdated);
    }
}
