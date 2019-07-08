package com.cay.sbt.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@ControllerAdvice
public class GlobalExceptionController {
    // @ResponseBody 返回json格式 @ExceptionHandler(RuntimeException.class) 定义异常格式，此处表示返回运行异常
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Map<String, Object> exceptionHandler(Exception e){
        log.info("全局异常信息捕获！");
        e.printStackTrace();
        Map<String,Object> errorMap = new HashMap<>();
        errorMap.put("errorKey","500");
        errorMap.put("errorValue","系统错误！");
        return errorMap;
    }
}
