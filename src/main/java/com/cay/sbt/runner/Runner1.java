package com.cay.sbt.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Runner1 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("初始化资源一。。。。。。。。。。。。。。。。。");
    }
}
