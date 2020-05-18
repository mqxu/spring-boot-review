package com.soft1851.springboot.schedule.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.hutool.http.HttpUtil;
import com.soft1851.springboot.schedule.model.Coder;
import com.soft1851.springboot.schedule.repository.CoderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: mq_xu
 * @date: 2020/5/17 12:33
 * @description:多线程异步任务
 */
@Component  //相对于@Repository和@Service，@Component用于那些比较中立的类的注解
@EnableScheduling //开启定时任务
@EnableAsync  //开启异步（多线程）
@Slf4j
public class MultithreadScheduleTask {
    @Resource
    private CoderRepository coderRepository;

    @Async
    @Scheduled(fixedRate = 1000)
    public void first() {
        Console.log("第一个异步定时任务,{},当前线程：{}", DateUtil.now(), Thread.currentThread().getName());
    }

    @Async
    @Scheduled(fixedRate = 3000)
    public void second() {
        Console.log("Task two,{},current thread：{}", RandomUtil.randomInt(10, 100), Thread.currentThread().getName());
    }

    @Async
    @Scheduled(fixedDelay = 2000)
    public void getCoder() {
        int index = RandomUtil.randomInt(1, 5);
        Coder coder = coderRepository.findById(index).get();
        download(coder);
    }

    @Async
    public void download(Coder coder) {
        String template = "D:/code/{}.jpg";
        String path = StrUtil.format(template, IdUtil.simpleUUID());
        HttpUtil.downloadFile(coder.getAvatar(), FileUtil.file(path));
        getQrCode(coder.getUrl(), path);
    }

    @Async
    public void getQrCode(String content, String logo) {
        String template = "D:/code/{}.jpg";
        String file = StrUtil.format(template, IdUtil.simpleUUID());
        QrCodeUtil.generate(content, QrConfig.create().setImg(logo), FileUtil.file(file));
    }
}

