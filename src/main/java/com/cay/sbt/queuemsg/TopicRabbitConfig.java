package com.cay.sbt.queuemsg;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {
    private static final String message ="topic.message";
    private static final String messages ="topic.messages";

    /**
     * 定义队列queueMessage
     * */
    @Bean
    public Queue queueMessage(){
        return new Queue(TopicRabbitConfig.message);
    }

    /**
     * 定义队列queueMessages
     * */
    @Bean
    public Queue queueMessages(){
        return new Queue(TopicRabbitConfig.messages);
    }

    /**
     * 定义topicExchange
     * */
    @Bean
    TopicExchange exchange(){
        return new TopicExchange("topicExchange");
    }

    /**
     * 将queueMessage队列与exchange绑定
     * routing key 为 topic.message 只能匹配topic.message
     * */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage,TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    /**
     * 将queueMessages队列与exchange绑定
     * routing key 为 topic.# 匹配topic开头的
     * */
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages,TopicExchange exchange){
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
}
