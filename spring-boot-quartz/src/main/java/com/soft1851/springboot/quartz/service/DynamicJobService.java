package com.soft1851.springboot.quartz.service;

import com.soft1851.springboot.quartz.entity.JobEntity;
import com.soft1851.springboot.quartz.job.DynamicJob;
import com.soft1851.springboot.quartz.repository.JobEntityRepository;
import org.quartz.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: mq_xu
 * @date: 2020/5/18
 * @description: 动态任务业务类
 */
@Service
public class DynamicJobService {

    @Resource
    private JobEntityRepository jobEntityRepository;

    /**
     * 通过Id获取Job
     *
     * @param id
     * @return
     */
    public JobEntity getJobEntityById(Integer id) {
        return jobEntityRepository.getById(id);
    }

    /**
     * 从数据库中加载获取到所有Job
     *
     * @return
     */
    public List<JobEntity> loadJobs() {
        return jobEntityRepository.findAll();
    }

    /**
     * 获取JobDataMap.(Job参数对象)
     *
     * @param job
     * @return
     */
    public JobDataMap getJobDataMap(JobEntity job) {
        JobDataMap map = new JobDataMap();
        map.put("name", job.getName());
        map.put("jobGroup", job.getJobGroup());
        map.put("cronExpression", job.getCron());
        map.put("parameter", job.getParameter());
        map.put("jobDescription", job.getDescription());
        map.put("vmParam", job.getVmParam());
        map.put("jarPath", job.getJarPath());
        map.put("status", job.getStatus());
        return map;
    }

    /**
     * 获取JobDetail,JobDetail是任务的定义,而Job是任务的执行逻辑,JobDetail里会引用一个Job Class来定义
     *
     * @param jobKey
     * @param description
     * @param map
     * @return
     */
    public JobDetail getJobDetail(JobKey jobKey, String description, JobDataMap map) {
        return JobBuilder.newJob(DynamicJob.class)
                .withIdentity(jobKey)
                .withDescription(description)
                .setJobData(map)
                .storeDurably()
                .build();
    }

    /**
     * 获取Trigger (Job的触发器,执行规则)
     *
     * @param job
     * @return
     */
    public Trigger getTrigger(JobEntity job) {
        return TriggerBuilder.newTrigger()
                .withIdentity(job.getName(), job.getJobGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron()))
                .build();
    }

    /**
     * 获取JobKey,包含Name和Group
     *
     * @param job
     * @return
     */
    public JobKey getJobKey(JobEntity job) {
        return JobKey.jobKey(job.getName(), job.getJobGroup());
    }
}