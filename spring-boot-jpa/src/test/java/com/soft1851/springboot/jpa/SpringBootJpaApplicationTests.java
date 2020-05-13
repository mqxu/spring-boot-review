package com.soft1851.springboot.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootTest
class SpringBootJpaApplicationTests {
    @Resource
    private DataSource dataSource;

    @Test
    void contextLoads() {
        System.out.println(dataSource);
    }

}
