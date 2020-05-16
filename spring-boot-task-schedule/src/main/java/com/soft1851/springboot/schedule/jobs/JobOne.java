package com.soft1851.springboot.schedule.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author: mq_xu
 * @date: 2020/5/16
 * @description:执行任务的逻辑类
 */
@Slf4j
@DisallowConcurrentExecution
public class JobOne implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String time = LocalDateTime.ofInstant(new Date().toInstant(),
                ZoneId.systemDefault()).
                format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info(time.concat("JobOne.execute"));

        //获取JobDataMap
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        log.info(jobDataMap.getString("userName"));
        log.info(jobDataMap.getString("passWord"));
    }
}