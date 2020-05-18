package com.soft1851.springboot.quartz.repository;


import com.soft1851.springboot.quartz.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: mq_xu
 * @date: 2020/5/18 19:22
 * @description: Job实体接口
 */
public interface JobEntityRepository extends JpaRepository<JobEntity, Integer> {

    /**
     * 根据id获取Job
     *
     * @param id
     * @return
     */
    JobEntity getById(Integer id);

}