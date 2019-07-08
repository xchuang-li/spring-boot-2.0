package com.cay.sbt.queuemsg;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitConfig {
    @Bean
    public Queue fanoutQueueA(){
        return new Queue("fanoutQueueA");
    }
    @Bean
    public Queue fanoutQueueB(){
        return new Queue("fanoutQueueB");
    }
    @Bean
    public Queue fanoutQueueC(){
        return new Queue("fanoutQueueC");
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Binding bindingQueueAExchange(Queue fanoutQueueA,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueueA).to(fanoutExchange);
    }
    @Bean
    public Binding bindingQueueBExchange(Queue fanoutQueueB,FanoutExchange fanoutExchange){
       return BindingBuilder.bind(fanoutQueueB).to(fanoutExchange);
    }
    @Bean
    public Binding bindingQueueCExchange(Queue fanoutQueueC,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueueC).to(fanoutExchange);
    }
}
