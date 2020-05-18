package com.soft1851.springboot.schedule.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @author: mq_xu
 * @date: 2020/5/18 10:31
 * @description: 封装了Quartz 动态添加、修改和删除定时任务时间的方法
 */
@Slf4j
public class QuartzManager {
    /**
     * 这个工厂对象是线程不安全的
     */
    private static SchedulerFactory factory = new StdSchedulerFactory();

    /**
     * 功能：添加一个定时任务
     *
     * @param jobName          任务名
     * @param jobGroupName     任务组名
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param jobClass         任务类
     * @param interval         间隔
     * @param objects          需要传递给执行任务的信息
     * @throws SchedulerException
     */
    public static void addJob(String jobName, String jobGroupName, String triggerName,
                              String triggerGroupName, Class<? extends Job> jobClass, int interval,
                              Object... objects) throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName)
                .build();

        if (objects != null) {
            for (int i = 0; i < objects.length; i++) {
                jobDetail.getJobDataMap().put("data" + (i + 1), objects[i]);
            }
        }
        Date startTime = new Date();
        startTime.setTime(startTime.getTime() + interval * 1000);
        //创建触发器对象
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
                .startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(interval).repeatForever())
                .build();

        //任务调度器
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        //启动
        if (!scheduler.isShutdown()) {
            scheduler.start();
            log.info("任务{}启动,触发器为{},执行间隔为{}", jobName, triggerName, interval);
        }
    }

    /**
     * 修改一个任务触发时间
     *
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param interval         间隔
     */
    public static void modifyJobTime(String triggerName, String triggerGroupName,
                                     int interval) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        SimpleTrigger trigger = (SimpleTrigger) scheduler.getTrigger(triggerKey);
        if (trigger == null) {
            return;
        }
        //获取正在用的触发器的cron表达式
        int oldInterval = (int) trigger.getRepeatInterval();
        //判断老的间隔和新的间隔是不是一样
        if (interval != oldInterval) {
            Date startTime = new Date();
            startTime.setTime(startTime.getTime() + interval * 1000);
            SimpleTrigger newTrigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
                    .startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(interval).repeatForever())
                    .build();
            // 修改任务的触发时间
            scheduler.rescheduleJob(triggerKey, newTrigger);
            log.info("触发器{}修改,新的执行间隔为{}", triggerName, interval);
        }
    }

    /**
     * 功能：删除一个任务，使用这个方法即使任务都删除完了，程序也不会停止，因为调度器还在一直等待着任务
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     * @throws SchedulerException
     */
    public static void removeJob(String jobName, String jobGroupName,
                                 String triggerName, String triggerGroupName) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        // 停止触发器
        scheduler.pauseTrigger(triggerKey);
        // 移除触发器
        scheduler.unscheduleJob(triggerKey);
        // 删除任务
        scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
        log.info("任务{}被删除", jobName);
    }

    /**
     * 启动所有任务
     */
    public static void startJobs() {
        try {
            Scheduler scheduler = factory.getScheduler();
            scheduler.start();
            log.info("所有任务启动！");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭所有任务，其实是关闭调度器。会让程序结束
     */
    public static void shutdownJobs() {
        try {
            Scheduler scheduler = factory.getScheduler();
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
                log.info("所有任务关闭！");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
