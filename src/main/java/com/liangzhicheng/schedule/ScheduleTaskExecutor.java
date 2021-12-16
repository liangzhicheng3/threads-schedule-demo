package com.liangzhicheng.schedule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduleTaskExecutor {

    private static Logger logger = LogManager.getLogger(ScheduleTaskExecutor.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    private int count;

    @Async(value = "threadPoolTaskExecutor")
    @Scheduled(cron = "*/6 * * * * ?")
    public void process(){
        logger.info("任务调度正在执行 {}", count++);
    }

    @Async(value = "threadPoolTaskExecutor")
    @Scheduled(fixedRate = 6000)
    public void currentTime(){
        logger.info("当前时间 {}", sdf.format(new Date()));
    }

}
