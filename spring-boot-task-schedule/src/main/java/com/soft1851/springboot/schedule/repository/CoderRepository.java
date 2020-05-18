package com.soft1851.springboot.schedule.repository;

import com.soft1851.springboot.schedule.model.Coder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: mq_xu
 * @date: 2020/5/17 13:56
 * @description:
 */
public interface CoderRepository extends JpaRepository<Coder, Integer> {
    /**
     * 按id修改avatar
     *
     * @param avatar
     * @param id
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update coder set avatar = ?1 where id = ?2", nativeQuery = true)
    void updateAvatar(String avatar, int id);
}
