package com.soft1851.springboot.schedule.task;

import com.soft1851.springboot.schedule.model.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;
import java.util.List;

/**
 * @author: mq_xu
 * @date: 2020/5/18 17:05
 * @description:
 */
public class JobTest {
//    private static SchedulerFactory sf = new StdSchedulerFactory();
//    private static String JOB_GROUP_NAME = "jobGroup1";
//    private static String TRIGGER_GROUP_NAME = "triggerGroup1";

    public static void main(String[] args) throws SchedulerException, ParseException {
        startSchedule();
        //resumeJob();
    }

    /**
     * 开始一个simpleSchedule()调度
     */
    public static void startSchedule() {
        try {
            // 1、创建一个JobDetail实例，指定Quartz
            JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                    // 任务名，任务组
                    .withIdentity("job1_1", "jGroup1")
                    .build();
            // 2、创建Trigger
            SimpleScheduleBuilder builder = SimpleScheduleBuilder
                    // 设置执行次数
                    .repeatSecondlyForTotalCount(100);
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1_1", "tGroup1").startNow()
                    .withSchedule(builder).build();
            // 3、创建Scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            // 4、调度执行
            scheduler.scheduleJob(jobDetail, trigger);
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            scheduler.shutdown();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从数据库中找到已经存在的job，并重新恢复调度
     */
    public static void resumeJob() {
        try {

            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            // 1、获取调度器中所有的触发器组
            List<String> triggerGroups = scheduler.getTriggerGroupNames();
            // 2、重新恢复在tGroup1组中，名为trigger1_1触发器的运行
            for (String triggerGroup : triggerGroups) {
                List<String> triggers = scheduler.getTriggerGroupNames();
                for (String trigger : triggers) {
                    Trigger tg = scheduler.getTrigger(new TriggerKey(trigger, triggerGroup));
                    // 根据名称判断
                    if (tg instanceof SimpleTrigger
                            && "tGroup1.trigger1_1".equals(tg.getDescription())) {
                        // 恢复运行
                        scheduler.resumeJob(new JobKey(trigger,
                                triggerGroup));
                    }
                }

            }
            scheduler.start();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
