package com.cay.sbt;

import com.cay.sbt.queuemsg.FanoutQueueSender;
import com.cay.sbt.queuemsg.HelloSender1;
import com.cay.sbt.queuemsg.HelloSender2;
import com.cay.sbt.queuemsg.TopicMessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqHelloTest {
    @Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloSender2 helloSender2;
    @Autowired
    private TopicMessageSender topicMessageSender;
    @Autowired
    private FanoutQueueSender fanoutQueueSender;

    @Test
    public void hello1() throws Exception{
        helloSender1.send("你好1，嘿嘿嘿！");
    }
    @Test
    public void hello2() throws Exception{
        helloSender2.send("你好2，嘿嘿嘿！");
    }
    @Test
    public void oneToMany(){
        for(int i=0;i<100;i++){
            helloSender1.send(String.valueOf(i));
//            helloSender2.send(String.valueOf(i));
        }
    }
    @Test
    public void topicSend1(){
        topicMessageSender.send1("topic1 !!!!!!!!!!!!!!");
    }
    @Test
    public void topicSend2(){
        topicMessageSender.send1("topic1 !!!!!!!!!!!!!!");
    }
    @Test
    public void fanoutSend(){
        fanoutQueueSender.send("Fanout Queue !!!");
    }
}
