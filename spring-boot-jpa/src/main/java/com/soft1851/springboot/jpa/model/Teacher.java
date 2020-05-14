package com.soft1851.springboot.jpa.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: mq_xu
 * @date: 2020/5/14 12:24
 * @description:
 */
@Entity
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "teacher_name", nullable = false, length = 30)
    private String teacherName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clazz_id", referencedColumnName = "id")
    private Clazz clazz;
}
