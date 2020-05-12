package com.soft1851.springboot.config;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author mq_xu
 */
@SpringBootApplication
public class SpringBootConfigApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SpringBootConfigApplication.class, args);
        new SpringApplicationBuilder(SpringBootConfigApplication.class)
                .bannerMode(Banner.Mode.CONSOLE).run(args);
    }

}
