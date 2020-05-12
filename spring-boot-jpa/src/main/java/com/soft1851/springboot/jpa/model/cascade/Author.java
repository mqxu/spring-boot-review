package com.soft1851.springboot.jpa.model.cascade;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author: mq_xu
 * @date: 2020/5/12 18:24
 * @description:
 */
@Data
@Entity
public class Author {
    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id; //id

    @Column(nullable = false, length = 20)
    private String name;//姓名

    //文章列表
    // 级联保存、更新、删除、刷新;延迟加载。当删除用户，会级联删除该用户的所有文章
    // 拥有mappedBy注解的实体类为关系被维护端
    // mappedBy="author"中的author是Article中的author属性
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Article> articleList;
}