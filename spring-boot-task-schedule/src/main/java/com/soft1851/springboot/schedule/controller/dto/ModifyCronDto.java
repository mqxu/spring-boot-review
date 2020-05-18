package com.soft1851.springboot.schedule.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author: mq_xu
 * @date: 2020/5/18
 * @description: 修改cron表达式的dto类
 * @description: 修改cron表达式的dto类
 */
@Data
public class ModifyCronDto {
    @NotNull(message = "the job id cannot be null")
    private Integer id;

    @NotEmpty(message = "the cron cannot be empty")
    private String cron;
}
