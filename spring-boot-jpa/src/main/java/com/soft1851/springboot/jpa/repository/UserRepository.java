package com.soft1851.springboot.jpa.repository;

import com.soft1851.springboot.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: mq_xu
 * @date: 2020/5/13 0:19
 * @description:基础的UserRepository，从父接口继承CRUD findBy返回一个User，findUsersBy返回一组User
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据方法名解析：按userName和password相等查询唯一记录
     *
     * @param userName
     * @param password
     * @return
     */
    User findByUserNameEqualsAndPasswordEquals(String userName, String password);

    /**
     * 根据方法名解析：按nickName模糊查询一组记录
     *
     * @param nickName
     * @return
     */
    List<User> findUsersByNickNameLike(String nickName);

    /**
     * 根据方法名解析：查询年龄大于指定age的所有用户
     *
     * @param age
     * @return
     */
    List<User> findUsersByAgeGreaterThan(int age);

    /**
     * 自定义JPQL查询，类似 Hibernate的 HQL语法，在接口上使用 @Query
     *
     * @param id
     * @return
     */
    @Query("select u from User u where u.id = ?1")
    User findById(long id);

    /**
     * 按id修改nickName
     *
     * @param nickName
     * @param id
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update user set nick_name = ?1 where id = ?2", nativeQuery = true)
    int updateNickName(String nickName, long id);


    /**
     * 插入
     *
     * @param userName
     * @param password
     * @param email
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query(value = "insert into user(user_name, password,email) values (:userName, :password,:email)", nativeQuery = true)
    int insertUser(@Param("userName") String userName, @Param("password") String password, @Param("email") String email);
}
