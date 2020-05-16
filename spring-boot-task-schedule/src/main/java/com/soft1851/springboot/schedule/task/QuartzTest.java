package com.soft1851.springboot.schedule.task;

import com.soft1851.springboot.schedule.jobs.HelloJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author: mq_xu
 * @date: 2020/5/16 15:46
 * @description:
 */
public class QuartzTest {
    public static void main(String[] args) {

        try {
            // 从工厂获取调度程序实例
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // 启动
            scheduler.start();
            // 定义作业并将其绑定到HelloJob类
            JobDetail job = newJob(HelloJob.class)
                    .withIdentity("myJob", "group1")
                    .build();

            // 触发作业运行，然后每40秒重复一次
            Trigger trigger = newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(40)
                            .repeatForever())
                    .build();

            // 告诉quartz使用我们的触发器来安排工作
            scheduler.scheduleJob(job, trigger);

            // 停止
            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
