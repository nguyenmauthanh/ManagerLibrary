package com.elcom.managerlibrary.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkerConfig {

    public static final String WORKER_QUEUE = "ELCOM_WORKER_QUEUE";

    @Bean(WORKER_QUEUE)
    public Queue queue(){
        return new Queue(WORKER_QUEUE);
    }

    public static class ReceiverConfig{

        @Bean
        public WorkerReceiver workerReceiver1(){
            return new WorkerReceiver(3);
        }

        @Bean
        public WorkerReceiver workerReceiver2(){
            return new WorkerReceiver(2);
        }

        @Bean
        public WorkerReceiver workerReceiver3(){
            return new WorkerReceiver(1);
        }
    }

    @Bean
    public WorkerSender workerSender(){
        return new WorkerSender();
    }
}
