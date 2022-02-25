package com.elcom.managerlibrary.rabbitmq;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RpcClientConfig {
    @Bean("rpcClientExchange")
    public DirectExchange rpcClientExchange() {
        return new DirectExchange("rpc_exchange");
    }
    @Bean
    public RpcClient rpcClient(){
        return new RpcClient();
    }
}
