package com.soft1851.springboot.config.controller;

import com.soft1851.springboot.config.model.Music;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: mq_xu
 * @date: 2020/5/12
 * @description:
 */
@RestController
public class MusicController {
    public final Music music;

    public MusicController(Music music) {
        this.music = music;
    }

    @GetMapping("/music")
    public Music music() {
        return music;
    }

}
