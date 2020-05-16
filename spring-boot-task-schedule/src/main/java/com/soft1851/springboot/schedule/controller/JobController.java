package com.soft1851.springboot.schedule.controller;

import com.soft1851.springboot.schedule.model.Task;
import com.soft1851.springboot.schedule.service.QuartzJobService;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: mq_xu
 * @date: 2020/5/16
 * @description:Job控制器
 */
@RestController
public class JobController {

    @Resource
    QuartzJobService quartzJobService;

    /**
     * 创建&启动
     *
     * @param task
     * @return
     * @throws SchedulerException
     * @throws ClassNotFoundException
     */
    @PostMapping("startJob")
    public String startJob(@RequestBody Task task) throws SchedulerException, ClassNotFoundException {
        quartzJobService.scheduleJob(task);
        return "startJob Success!";
    }

    /**
     * 暂停
     *
     * @param task
     * @return
     * @throws SchedulerException
     */
    @PostMapping("pauseJob")
    public String pauseJob(@RequestBody Task task) throws SchedulerException {
        quartzJobService.pauseJob(task.getJobKey());
        return "pauseJob Success!";
    }

    /**
     * 恢复
     *
     * @param task
     * @return
     * @throws SchedulerException
     */
    @PostMapping("resumeJob")
    public String resumeJob(@RequestBody Task task) throws SchedulerException {
        quartzJobService.resumeJob(task.getJobKey());
        return "resumeJob Success!";
    }

    /**
     * 删除
     *
     * @param task
     * @return
     * @throws SchedulerException
     */
    @PostMapping("delJob")
    public String delJob(@RequestBody Task task) throws SchedulerException {
        quartzJobService.deleteJob(task.getJobKey());
        return "delJob Success!";
    }

    /**
     * 修改
     *
     * @param task
     * @return
     */
    @PostMapping("modifyJob")
    public String modifyJob(@RequestBody Task task) {
        if (quartzJobService.modifyJobCron(task)) {
            return "modifyJob Success!";
        } else {
            return "modifyJob Fail!";
        }
    }
}