package com.elcom.managerlibrary.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Pub_SubConfig {
    private static final String FANOUT_EXCHANGE = "fanout_exchange";

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    public static class ReceiverConfig{

        @Bean
        public Queue queue1() { return new AnonymousQueue();
        }

        @Bean
        public Queue queue2(){
            return new AnonymousQueue();
        }

        @Bean
        public Queue queue3(){
            return new AnonymousQueue();
        }

        @Bean
        public Binding binding1(FanoutExchange fanoutExchange, Queue queue1){
            return BindingBuilder.bind(queue1).to(fanoutExchange);
        }
        @Bean
        public Binding binding2(FanoutExchange fanoutExchange, Queue queue2){
            return BindingBuilder.bind(queue2).to(fanoutExchange);
        }
        @Bean
        public Binding binding3(FanoutExchange fanoutExchange, Queue queue3){
            return BindingBuilder.bind(queue3).to(fanoutExchange);
        }

        @Bean
        public Pub_SubReceiver pub_subReceiver(){
            return new Pub_SubReceiver();
        }

    }
    @Bean
    public Pub_SubSender pub_subSender(){
        return new Pub_SubSender();
    }
}
