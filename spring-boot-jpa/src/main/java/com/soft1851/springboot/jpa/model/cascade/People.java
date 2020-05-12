package com.soft1851.springboot.jpa.model.cascade;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author: mq_xu
 * @date: 2020/5/12
 * @description:
 */
@Data
@Entity
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = true, length = 20)
    private String name;

    @Column(name = "sex", nullable = true, length = 1)
    private String sex;

    @Column(name = "birthday", nullable = true)
    private Timestamp birthday;

    /**
     * People是关系的维护端，当删除 people，会级联删除 address
     * people中的address_id字段参考address表中的id字段
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}