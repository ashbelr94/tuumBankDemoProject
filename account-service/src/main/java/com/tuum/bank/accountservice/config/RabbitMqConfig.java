package com.tuum.bank.accountservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.queue.account.name}")
    private String accountQueue;

    @Value("${rabbitmq.exchange.name}")
    private String bankExchange;

    @Value("${rabbitmq.routingKey.account.key}")
    private String accountRoutingKey;


    @Bean
    public Queue accountQueue(){
        return new Queue(accountQueue);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(bankExchange);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(accountQueue())
                .to(exchange())
                .with(accountRoutingKey);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
