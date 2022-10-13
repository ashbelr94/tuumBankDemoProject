package com.tuum.bank.transactionservice.publisher;

import com.tuum.bank.transactionservice.Events.TransactionEvent;
import com.tuum.bank.transactionservice.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TransactionProducer {

    private Logger LOGGER = LoggerFactory.getLogger(TransactionProducer.class);

    @Value("${rabbitmq.exchange.name}")
    private String bankExchange;

    @Value("${rabbitmq.routingKey.transaction.key}")
    private String transactionRoutingKey;

    private RabbitTemplate rabbitTemplate;

    public TransactionProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(TransactionEvent transactionEvent){
        LOGGER.info(String.format("Transaction Event Created: %s", transactionEvent.toString()));
        rabbitTemplate.convertAndSend(bankExchange, transactionRoutingKey, transactionEvent);

    }
}
