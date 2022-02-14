package com.elcom.managerlibrary.rabbitmq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RpcConfig {

    private static class RpcClientConfig {
        @Bean("rpcClientExchange")
        public DirectExchange rpcClientExchange() {
            return new DirectExchange("rpc_exchange");
        }
        @Bean
        public RpcClient rpcClient() {
            return new RpcClient();
        }
    }

    private static class RpcServerConfig {
        @Bean("rpcQueue")
        public Queue rpcQueue() {
            return new Queue("rpc_requests");
        }
        @Bean("rpcServerExchange")
        public DirectExchange rpcServerExchange() {
            return new DirectExchange("rpc_exchange");
        }
        @Bean("rpcBinding")
        public Binding binding(DirectExchange rpcServerExchange, Queue rpcQueue) {
            return BindingBuilder.bind(rpcQueue).to(rpcServerExchange).with("rpc");
        }
        @Bean
        public RpcServer rpcServer() {
            return new RpcServer();
        }
    }
}
