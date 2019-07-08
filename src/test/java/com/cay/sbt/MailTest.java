package com.cay.sbt;

import com.cay.sbt.mail.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {
    @Autowired
    MailService mailService;

    @Test
    public void testMailSend() throws Exception{
        //mailService.sendSimpleMail("lixiaochuang@tansun.com.cn","TestMailSend","你好！");
        mailService.sendAttachmentsMail(
                "lixiaochuang@tansun.com.cn",
                "TestAttachmentsMail",
                "你好。。。",
                "D:\\mydata\\logs\\ssm1-log.log"
                );
    }
}
