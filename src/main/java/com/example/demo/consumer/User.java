package com.example.demo.consumer;

import com.example.demo.config.MessagingConfig;
import com.example.demo.model.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeOrder(OrderStatus orderStatus){
        System.out.println("message received from queue" +orderStatus);
    }
}
