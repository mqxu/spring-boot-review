package com.soft1851.springboot.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: mq_xu
 * @date: 2020/5/14 19:02
 * @description:通过@EnableScheduling开启定时
 */
@SpringBootApplication
//@EnableScheduling
//@EnableAsync
public class SpringBootTaskScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTaskScheduleApplication.class, args);
    }

}
