package com.cay.sbt.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 该类为代码注册自定义Filter
 *
 * */
@Slf4j
//标识此为配置类，此处注释掉，改为使用注解方式注册自定义Filter
//@Configuration
public class WebConfig {
    //注册自定义Filter
    @Bean
    public FilterRegistrationBean testFilterRegistrationBean(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("name","value");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;

    }
}
