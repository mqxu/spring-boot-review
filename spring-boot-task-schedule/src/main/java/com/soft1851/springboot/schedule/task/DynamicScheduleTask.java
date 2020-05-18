package com.soft1851.springboot.schedule.task;

import com.soft1851.springboot.schedule.job.TestJob;
import com.soft1851.springboot.schedule.quartz.QuartzManager;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;

/**
 * @author: mq_xu
 * @date: 2020/5/18
 * @description: 动态调度任务测试类
 */
@Slf4j
public class DynamicScheduleTask {
    public static void main(String[] args) throws SchedulerException {
//        //添加任务，注意各个参数的作用
        QuartzManager.addJob("testJob", "jobGroup1",
                "testTrigger", "triggerGroup1",
                TestJob.class, "0/5 * * * * ?");
//
//        //修改任务
//        QuartzManager.modifyJobTime("testTrigger", "triggerGroup1",
//                5);
//        try {
//            Thread.sleep(8000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        //删除任务
//        QuartzManager.removeJob("testJob", "jobGroup1",
//                "testTrigger", "triggerGroup1");
//
//        QuartzManager.shutdownJobs();


    }
}
