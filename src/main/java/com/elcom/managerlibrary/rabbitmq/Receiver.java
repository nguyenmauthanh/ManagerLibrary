package com.elcom.managerlibrary.rabbitmq;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = Config.ELCOM_QUEUE)
public class Receiver {

    @RabbitHandler
    public void receiver(String message){
        System.out.println("[x.....x] " + message + " !!!!!");
    }
}
