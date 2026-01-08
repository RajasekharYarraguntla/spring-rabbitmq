package com.raja.controller;

import com.raja.dto.UserCreated;
import com.raja.dto.UserUpdated;
import com.raja.producer.RabbitMQProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private RabbitMQProducer producer;

    @PostMapping("/users")
    public void save(@RequestBody @Validated UserCreated userCreated) {
        producer.sendUserCreatedMessage(userCreated);
    }

    @PutMapping("/users")
    public void update(@RequestBody @Validated UserUpdated userUpdated) {
        producer.sendUserUpdateMessage(userUpdated);
    }
}
