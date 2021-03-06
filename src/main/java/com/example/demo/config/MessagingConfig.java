package com.example.demo.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @Author denis
 * 21/6/2021
 */
@Configuration
public class MessagingConfig {

    public static final String QUEUE = "orderQueue";
    public static final String EXCHANGE ="orderExchange";
    public static final String ROUTING_KEY = "testKey";

    @Bean
    public Queue queue(){

        return new Queue(QUEUE);

    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);

    }

    @Bean
    public Jackson2JsonMessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
}
