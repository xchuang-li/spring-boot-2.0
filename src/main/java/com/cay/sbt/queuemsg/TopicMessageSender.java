package com.cay.sbt.queuemsg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TopicMessageSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send1(String msg){
        this.amqpTemplate.convertAndSend("topicExchange","topic.message",msg);
    }
    public void send2(String msg){
        this.amqpTemplate.convertAndSend("topicExchange","topic.messages",msg);
    }
}
