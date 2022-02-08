package com.elcom.managerlibrary.rabbitmq;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;



public class Sender {

    @Autowired
    public RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier(value = Config.ELCOM_QUEUE)
    private Queue queue;

    public void send(String message) {
        this.rabbitTemplate.convertAndSend(queue.getName(), message);
        System.out.println(" [x.....x] Sender " + queue.getName() + " queue sent '" + message + "!!!!!!!");
    }
}
