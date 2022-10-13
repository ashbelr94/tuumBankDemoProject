package com.tuum.bank.transactionservice.config;

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

    @Value("${rabbitmq.queue.transaction.name}")
    private String transactionQueue;

    @Value("${rabbitmq.exchange.name}")
    private String bankExchange;

    @Value("${rabbitmq.routingKey.transaction.key}")
    private String transactionRoutingKey;


    @Bean
    public Queue accountQueue(){
        return new Queue(transactionQueue);
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
                .with(transactionRoutingKey);
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
