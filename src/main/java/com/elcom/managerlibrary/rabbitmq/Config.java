package com.elcom.managerlibrary.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    public static final String ELCOM_QUEUE = "QUEUE";

    @Bean(ELCOM_QUEUE)
    public Queue queue(){
        return new Queue(ELCOM_QUEUE);
    }

    @Bean
    public Sender sender(){
        return new Sender();
    }

    @Bean
    public Receiver receiver(){
        return new Receiver();
    }
}
