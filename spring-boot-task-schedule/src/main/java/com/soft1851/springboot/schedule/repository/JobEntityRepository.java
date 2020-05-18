package com.soft1851.springboot.schedule.repository;

import com.soft1851.springboot.schedule.model.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: mq_xu
 * @date: 2020/5/18 19:18
 * @description:
 */
public interface JobEntityRepository extends JpaRepository<JobEntity, Integer> {

    /**
     * 根据id获取Job
     * @param id
     * @return
     */
    JobEntity getById(Integer id);

}