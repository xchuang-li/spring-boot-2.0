package com.cay.sbt.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect
@Component
public class WebLogAspect {
    public static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.cay.sbt.controller.*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("URL: ="+request.getRequestURI().toString());
        logger.info("HTTP_METHOD: ="+request.getMethod());
        logger.info("IP: ="+request.getRemoteAddr());
        logger.info("PORT: ="+request.getRemotePort());
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()){
            String paramName = (String)enu.nextElement();
            logger.info("paramName = {},paramValue = {}",paramName,request.getParameter(paramName));
        }
    }

    @AfterReturning(returning = "object",pointcut = "webLog()")
    public void doAfterReturning(Object object){
        logger.info("RESPONSE: ="+object);
    }
}
