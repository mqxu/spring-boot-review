package com.soft1851.springboot.schedule.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.util.Date;

/**
 * @author: mq_xu
 * @date: 2020/5/16 15:54
 * @description:
 */
@DisallowConcurrentExecution    //相同定义的jobDetail不能并发执行
@PersistJobDataAfterExecution   //jobDataMap数据保存
@Slf4j
public class HelloJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("springboot整合定时任务Quartz:" + new Date());
    }
}
