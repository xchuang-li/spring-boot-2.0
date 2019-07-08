package com.cay.sbt.queuemsg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FanoutQueueSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String msg){
        this.amqpTemplate.convertAndSend("fanoutExchange","",msg);
    }
}
