package com.cay.sbt.queuemsg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloSender1 {
    @Autowired
    private AmqpTemplate amqpTemplate;
    public void send(String msg){
        this.amqpTemplate.convertAndSend("hello-queue",msg);
    }
}
