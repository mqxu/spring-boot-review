package com.soft1851.springboot.schedule.job;

import com.soft1851.springboot.schedule.repository.CoderRepository;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;

/**
 * @author: mq_xu
 * @date: 2020/5/18 11:01
 * @description: 操作Coder的任务为在指定的时间去修改其信息
 */
public class CoderJob implements Job {
    @Resource
    private CoderRepository coderRepository;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap map = jobExecutionContext.getMergedJobDataMap();
        Object data1 = map.get("data1");
        Object data2 = map.get("data2");
        coderRepository.updateAvatar(data1.toString(), (int) data2);
    }
}
