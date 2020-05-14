package com.soft1851.springboot.schedule.task;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author: mq_xu
 * @date: 2020/5/14 19:02
 * @description:
 */
//@Component
public class Task1 {
    private int count = 0;

    /**
     * 设置 process() 每隔5秒执行一次，并统计执行的次数
     */
    @Scheduled(cron = "*/5 * * * * ?")
    private void process() {
        System.out.println("this is scheduler task running  " + (count++));
    }
}
