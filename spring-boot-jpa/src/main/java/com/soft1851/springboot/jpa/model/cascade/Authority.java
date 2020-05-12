package com.soft1851.springboot.jpa.model.cascade;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author: mq_xu
 * @date: 2020/5/12
 * @description:
 */
@Data
@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限名
     */
    @Column(nullable = false)
    private String name;

    /**
     * 拥有权限的用户列表
     */
    @ManyToMany(mappedBy = "authorityList")
    private List<User> userList;
}