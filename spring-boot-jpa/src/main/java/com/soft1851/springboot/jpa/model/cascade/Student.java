package com.soft1851.springboot.jpa.model.cascade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Yujie_Zhao
 * @ClassName Student
 * @Description TODO
 * @Date 2020/5/13  17:50
 * @Version 1.0
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    @Column(nullable = false, unique = true)
    private Integer clazzId;

    @Column(nullable = false, unique = true)
    private String studentName;

    @Column(nullable = false, unique = true)
    private String hometown;

    @Column
    private LocalDateTime birthday;

    /**
     * 级联查询
     */
//    @OneToOne(mappedBy = "student", cascade = {CascadeType.MERGE,
//            CascadeType.REFRESH}, optional = false)
//    private Clazz clazz;


    //可选属性optional=false, 表示 author不能为空。删除文章，不影响用户
//    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
//    @JoinColumn(name = "teacher_Id")
//    private Teacher teacher;//所属作者

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "class_id")
    private Clazz clazz;//所属作者
}
