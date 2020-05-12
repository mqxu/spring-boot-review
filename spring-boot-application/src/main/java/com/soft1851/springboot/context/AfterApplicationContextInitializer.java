package com.soft1851.springboot.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;

/**
 * @author: mq_xu
 * @date: 2020/5/12 11:33
 * @description:
 */
@Slf4j
public class AfterApplicationContextInitializer<C extends ConfigurableApplicationContext>
        implements ApplicationContextInitializer<C>, Ordered {


    @Override
    public void initialize(C applicationContext) {
        log.info("After Application.id = " + applicationContext.getId());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
