package com.soft1851.springboot.jpa.service.impl;

import com.soft1851.springboot.jpa.dao.MessageRepository;
import com.soft1851.springboot.jpa.model.Message;
import com.soft1851.springboot.jpa.service.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @author: mq_xu
 * @date: 2020/5/12 18:36
 * @description:
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageRepository messageRepository;

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    public void batchSave(List<Message> messages) {
        messageRepository.saveAll(messages);
    }

    @Override
    public void delete(Integer id) {
        messageRepository.deleteById(id);
    }

    @Override
    public void batchDelete(List<Message> messages) {
        // 使用一条 SQL
        messageRepository.deleteInBatch(messages);
        // 使用多条 SQL
        // messageRepository.deleteAll(messages);
    }

    @Override
    public void deleteAll() {
        messageRepository.deleteAllInBatch();
        // messageRepository.deleteAll();
    }

    @Override
    public void update(Message message) {
        messageRepository.saveAndFlush(message);
    }

    /**
     * 获取持久化管理器
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * 批量更新方法，批量插入、删除做法类似
     *
     * @param messages
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void batchUpdate(List<Message> messages) {
        messages.forEach(message -> {
            em.merge(message);
        });
        em.flush();
        em.clear();
    }

    @Override
    public long count() {
        return messageRepository.count();
    }

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message getMessage(Integer id) {
        // findOne的速度快于findById
        Optional<Message> msg = messageRepository.findById(id);
        // Optional<Message> msg = messageRepository.findOne(Example.of(Message.builder().msgId(id).build()));
        return msg.orElse(null);
    }
}
