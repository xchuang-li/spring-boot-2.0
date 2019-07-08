package com.cay.sbt.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SchedulerTask1 {
    private int count = 0;

    @Scheduled(cron = "*/6 * * * * ?")
    private void process1(){
        log.info("SchedulerTask1 process1--> count:="+(count++));
    }

    @Scheduled(cron = "*/6 * * * * ?")
    private void process2(){
        log.info("SchedulerTask1 process2--> count:="+(count++));
    }

}
