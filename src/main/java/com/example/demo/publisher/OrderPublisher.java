package com.example.demo.publisher;


import com.example.demo.config.MessagingConfig;
import com.example.demo.model.Order;
import com.example.demo.model.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName){

        order.setOrderId(UUID.randomUUID().toString());

        //restaurant service
        //payment service

        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed successfully"+restaurantName);
        template.convertAndSend( MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY,orderStatus);
        return "Success !";


    }
}
