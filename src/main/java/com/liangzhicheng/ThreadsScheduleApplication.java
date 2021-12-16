package com.liangzhicheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling //定时器注解，启用定时器功能
@SpringBootApplication
public class ThreadsScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadsScheduleApplication.class, args);
    }

}
