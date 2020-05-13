package com.soft1851.springboot.jpa.repository;

import com.soft1851.springboot.jpa.model.User;
import com.soft1851.springboot.jpa.repository.test1.UserTest1Repository;
import com.soft1851.springboot.jpa.repository.test2.UserTest2Repository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 多数据源测试
 */
@SpringBootTest
@Slf4j
class UserRepositoryTest1 {
    @Resource
    private UserTest1Repository userTest1Repository;
    @Resource
    private UserTest2Repository userTest2Repository;

    @Test
    void testSave() {
        User user = User.builder()
                .userName("aaa")
                .email("aaa@qq.com")
                .password("aaa123")
                .nickName("aaabbb")
                .age(11)
                .regTime(LocalDateTime.now()).build();
        userTest1Repository.save(user);
        userTest2Repository.save(user);

        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            User testUser = User.builder()
                    .userName("user" + i)
                    .email("user" + i + "@qq.com")
                    .password("123456")
                    .nickName("nickName" + i)
                    .age(new Random().nextInt(50))
                    .regTime(LocalDateTime.now()).build();
            users.add(testUser);
        }
        userTest1Repository.saveAll(users);
        userTest2Repository.saveAll(users);
    }

    @Test
    void testSelectAll() {
        List<User> list1 = userTest1Repository.findAll();
        log.info(String.valueOf(list1.size()));
        List<User> list2 = userTest2Repository.findAll();
        log.info(String.valueOf(list2.size()));
    }
}