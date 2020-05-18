package com.soft1851.springboot.schedule.quartz;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author: mq_xu
 * @date: 2020/5/18 10:34
 * @description:
 */
public class TestJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Console.log("当前时间：{}", DateUtil.now());
    }
}
