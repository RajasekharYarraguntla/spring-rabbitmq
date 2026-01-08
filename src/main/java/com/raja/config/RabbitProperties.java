package com.raja.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitProperties {

    private Exchange exchange;
    private Map<String, QueueConfig> queues;


    @Getter
    @Setter
    public static class Exchange {
        private String name;
    }

    @Getter
    @Setter
    public static class QueueConfig {
        private String queueName;
        private String routingKey;
    }
}
