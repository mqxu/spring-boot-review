package com.soft1851.springboot.schedule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author: mq_xu
 * @date: 2020/5/17 13:50
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * 主页地址
     */
    @Column(nullable = false, length = 200)
    private String url;

    /**
     * 头像
     */
    @Column(nullable = false, length = 200)
    private String avatar;

}
