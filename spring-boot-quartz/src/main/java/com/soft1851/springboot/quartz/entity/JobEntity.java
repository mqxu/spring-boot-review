package com.soft1851.springboot.quartz.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author: mq_xu
 * @date: 2020/5/18 19:16
 * @description: 作业实体类
 */
@Entity
@Data
@Accessors(chain = true)
public class JobEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * job名称
     */
    private String name;
    /**
     * job组名
     */
    private String jobGroup;
    /**
     * 执行的cron
     */
    private String cron;
    /**
     * job的参数
     */
    private String parameter;
    /**
     * job描述信息
     */
    private String description;
    /**
     * vm参数
     */
    private String vmParam;
    /**
     * job的jar路径
     */
    private String jarPath;
    /**
     * job的执行状态,这里我设置为OPEN/CLOSE且只有该值为OPEN才会执行该Job
     */
    private String status;
}