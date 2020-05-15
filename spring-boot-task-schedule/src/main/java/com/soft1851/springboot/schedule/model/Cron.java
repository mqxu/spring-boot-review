package com.soft1851.springboot.schedule.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: mq_xu
 * @date: 2020/5/15 14:30
 * @description:Cron表达式实体类
 */
@Data
@Entity
public class Cron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cronId;

    @Column(name = "cron_name", nullable = false, length = 30)
    private String cronName;

    @Column(name = "cron", nullable = false, length = 50)
    private String cron;
}
