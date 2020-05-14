package com.soft1851.springboot.jpa.controller;

import com.soft1851.springboot.jpa.model.Student;
import com.soft1851.springboot.jpa.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: mq_xu
 * @date: 2020/5/14 15:57
 * @description:
 */
@RestController
@RequestMapping(value = "/api/student")
public class StudentController {
    @Resource
    private StudentRepository studentRepository;

    @GetMapping()
    public List<Student> getStudents(@RequestParam("clazzId") int id) {
        return studentRepository.findByClazzId(id);
    }
}
