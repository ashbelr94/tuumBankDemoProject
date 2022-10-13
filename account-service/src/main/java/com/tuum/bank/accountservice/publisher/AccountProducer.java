package com.tuum.bank.accountservice.publisher;


import com.tuum.bank.accountservice.event.AccountEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AccountProducer {

    private Logger LOGGER = LoggerFactory.getLogger(AccountProducer.class);

    @Value("${rabbitmq.exchange.name}")
    private String bankExchange;

    @Value("${rabbitmq.routingKey.account.key}")
    private String accountRoutingKey;

    private RabbitTemplate rabbitTemplate;

    public AccountProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(AccountEvent accountEvent){
        LOGGER.info(String.format("Account Event Created: %s", accountEvent.toString()));
        rabbitTemplate.convertAndSend(bankExchange,accountRoutingKey, accountEvent);
    }

}
