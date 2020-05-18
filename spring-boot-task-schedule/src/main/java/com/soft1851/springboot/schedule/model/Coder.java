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

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 255)
    private String url;

    @Column(nullable = false, length = 255)
    private String avatar;
}
