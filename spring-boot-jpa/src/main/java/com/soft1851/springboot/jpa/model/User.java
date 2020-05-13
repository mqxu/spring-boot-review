package com.soft1851.springboot.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author: mq_xu
 * @date: 2020/5/13
 * @description:unique唯一约束 四种主键生成策略：
 * TABLE, //使用一个额外的数据库表来保存主键
 * SEQUENCE,//使用序列的方式，且其底层数据库要支持序列，一般有postgres、Oracle等
 * IDENTITY,//主键由数据库生成，一般为自增型主键，支持的有MySql和Sql Server
 * AUTO//由程序来决定主键规则
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 主键，策略为自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * nullable = false为非空约束，unique = true是唯一约束
     */
    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 32)
    private String nickName;

    @Column
    private Integer age;

    @Column
    private LocalDateTime regTime;
}
