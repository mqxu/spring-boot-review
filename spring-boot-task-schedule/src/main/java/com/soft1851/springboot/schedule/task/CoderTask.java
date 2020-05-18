package com.soft1851.springboot.schedule.task;

import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

/**
 * @author: mq_xu
 * @date: 2020/5/18 14:45
 * @description: Coder相关任务调度
 */
@Component
public class CoderTask {
    /**
     * 添加作业
     *
     * @throws SchedulerException
     */
    public void addJob(String avatar, int id) throws SchedulerException {
//        Object[] objects = {avatar, id};
//        QuartzManager.addJob("coderJob1", "jobGroup1",
//                "coderTrigger1", "triggerGroup1",
//                CoderJob.class, 2, objects);
//        try {
//            Thread.sleep(60000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
