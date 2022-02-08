package com.elcom.managerlibrary.rabbitmq;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.concurrent.atomic.AtomicInteger;

public class WorkerSender {

    @Autowired
    public RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier(value = WorkerConfig.WORKER_QUEUE)
    private Queue queue;

    AtomicInteger dots = new AtomicInteger(0);
    AtomicInteger count = new AtomicInteger(0);

    public void send(String message){
        StringBuilder builder = new StringBuilder(message);
        if(dots.getAndIncrement() == 4){
            dots.set(1);
        }
        for(int i = 0; i < dots.get(); i++){
            builder.append('.');
        }
        builder.append(count.incrementAndGet());
        String message_new = builder.toString();
        rabbitTemplate.convertAndSend(queue.getName(), message_new);
        System.out.println("[......] Sent " + message_new + " !!!!!!!");
    }
}
