package com.soft1851.springboot.jpa.dao;

import com.soft1851.springboot.jpa.model.Message;
import com.soft1851.springboot.jpa.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
class MessageRepositoryTest {
    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void testSave() {
        Message message = Message.builder().msgText("软件1851").msgSummary("沉迷学习").build();
        // 保存单个对象
        messageRepository.save(message);

        List<Message> messages = new ArrayList<>(Arrays.asList(
                Message.builder().msgText("后端").msgSummary("SpringBoot").build(),
                Message.builder().msgText("前端").msgSummary("Vue.js").build(),
                Message.builder().msgText("移动端").msgSummary("Flutter").build()));
        // 保存多个
        messageRepository.saveAll(messages);
    }

    @Test
    public void testDelete() {
        Message message = Message.builder().msgId(1)
                .msgText("软件1851").msgSummary("沉迷学习").build();

        // 删除单条记录
        // 根据主键删除
        messageRepository.deleteById(1);
        // 或者参数为对象，根据主键删除
        messageRepository.delete(message);
        // 删除集合
        message = Message.builder().msgId(5)
                .msgText("Monday").msgSummary("study").build();
        List<Message> messages = new ArrayList<>();
        messages.add(message);
        message = Message.builder().msgId(6)
                .msgText("Tuesday").msgSummary("study").build();
        messages.add(message);
        // 删除集合：一条一条删除
        messageRepository.deleteAll(messages);
        // 删除集合：一条 sql，拼接 or语句
        messageRepository.deleteInBatch(messages);
        // 删除全部
        // 删除所有：先findAll，然后一条一条删除，最后提交事务
        messageRepository.deleteAll();
        // 删除所有：使用一条 sql
        messageRepository.deleteAllInBatch();
    }

    @Autowired
    private MessageService messageService;

    @Test
    public void testUpdate() {
        // 根据主键更新
        Message message = Message.builder().msgId(2)
                .msgText("后端架构").msgSummary("SpringCloud").build();
        messageRepository.saveAndFlush(message);

        // 批量更新
        List<Message> messages = new ArrayList<>();
        messages.add(Message.builder().msgId(5).msgText("workday").msgSummary("study").build());
        messages.add(Message.builder().msgId(6).msgText("weekend").msgSummary("play").build());
        messageService.batchUpdate(messages);
    }

    @Test
    public void testSelect() {
        // 查询所有
        messageRepository.findAll().forEach(msg -> log.info(msg.toString()));
        // 分页查询全部，返回封装了的分页信息， jpa页码从0开始
        Page<Message> pageInfo = messageRepository.findAll(
                PageRequest.of(1, 3, Sort.Direction.ASC, "msgId"));
        log.info("总记录数： {}", pageInfo.getTotalElements());
        log.info("当前页记录数： {}", pageInfo.getNumberOfElements());
        log.info("每页记录数： {}", pageInfo.getSize());
        log.info("获取总页数： {}", pageInfo.getTotalPages());
        log.info("查询结果： {}", pageInfo.getContent());
        log.info("当前页（从0开始计）： {}", pageInfo.getNumber());
        log.info("是否为首页： {}", pageInfo.isFirst());
        log.info("是否为尾页： {}", pageInfo.isLast());
        // 条件查询
        Message message = Message.builder().msgSummary("study").build();
        List<Message> messages = messageRepository.findAll(Example.of(message));
        log.info("满足条件的记录有：");
        messages.forEach(msg -> log.info(msg.toString()));
        // 单个查询
        Message msg = Message.builder().msgId(2).build();
        Optional<Message> optionalMessage = messageRepository.findOne(Example.of(msg));
        log.info("单个查询结果： {}", optionalMessage.orElse(null));
    }

    @Test
    public void testCustomSQL() {
        Integer num = messageRepository.insertMessage("自定义SQL", "JPA");
        log.info("增加的数据条数： {}", num);
        Integer updateNum = messageRepository.updateName("JPQL", 1);
        log.info("修改的数据条数： {}", updateNum);
    }
}