package com.cay.sbt.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Slf4j
@Component
public class MailService {

    public static final String from = "lixiaochuang@tansun.com.cn";

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 简单类型的邮件
     * */
    public void sendSimpleMail(String to,String subject,String context){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(context);
        simpleMailMessage.setFrom(from);

        javaMailSender.send(simpleMailMessage);
        log.info("邮件发送成功！");
    }

    /**
     * HTML格式的邮件
     * */
    public void sendHtmlMail(String to,String subject,String context) throws Exception{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(context,true);
        messageHelper.setFrom(from);

        javaMailSender.send(mimeMessage);
        log.info("邮件发送成功！");
    }
    /**
     * 带附件的邮件
     * */
    public void sendAttachmentsMail(String to,String subject,String context,String filePath)throws Exception{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(context,true);
        messageHelper.setFrom(from);

        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
        messageHelper.addAttachment(fileName,file);

        javaMailSender.send(mimeMessage);
        log.info("邮件发送成功！");
    }
}
