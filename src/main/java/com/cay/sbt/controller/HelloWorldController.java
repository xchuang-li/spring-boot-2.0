package com.cay.sbt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Controller 返回视图模式
 * @RestController  返回json字符串格式
 * @ResponseBody+@Controller=@RestController
 *
 * */

@Controller
public class HelloWorldController {
    public static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Value("${server.port}")
    private String port;

    @ResponseBody
    @RequestMapping(value = "/helloWorld",method = RequestMethod.GET)
    public String helloWorld(){
        logger.info("Hello World!"+port);
        return "Hello World";
    }
    @RequestMapping("/helloWorldFreeMarker")
    public String helloWorldFreeMarker(Map map){
        map.put("name","lucy");
        map.put("age","20");
        map.put("sex","2");

        List<Map<String,Object>> list1 = new ArrayList<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("henan","zhengzhou");
        map1.put("shanxi","taiyuan");
        map1.put("guangdong","guangzhou");
        map1.put("hebei","tianjin");
        map1.put("hubei","wuhan");
        list1.add(map1);

        map.put("list1",list1);
        return "helloWorldFreeMarker";
    }
    @ResponseBody
    @RequestMapping("/uid")
    public String getUid(HttpSession session){
        UUID uid = (UUID)session.getAttribute("uid");
        if(uid == null){
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid",uid);
        return session.getId();
    }
}
