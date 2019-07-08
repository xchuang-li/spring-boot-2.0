package com.cay.sbt.queuemsg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RabbitListener(queues = "topic.messages")
public class TopicMessagesReceiver {
    @RabbitHandler
    public void process(String msg){
        log.info("TopicMessageReceiver 2:"+msg);
    }
}
