package com.soft1851.springboot.schedule.model;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author: mq_xu
 * @date: 2020/5/18 19:30
 * @description:
 */
@Slf4j
public class MyJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(DateUtil.now());
    }
}
