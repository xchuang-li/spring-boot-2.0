package com.cay.sbt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @component 扫包范围
 * @EnableAutoConfiguration 自动加载
 * @component+@EnableAutoConfiguration=@SpringBootApplication 扫包范围为同级和子包
 * */
@SpringBootApplication
//Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册
@ServletComponentScan
//开启定时任务
//@EnableScheduling
public class SbtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbtApplication.class, args);
    }

}
