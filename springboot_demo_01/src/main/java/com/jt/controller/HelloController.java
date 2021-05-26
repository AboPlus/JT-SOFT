package com.jt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //向前端返回JSON JSON：特殊格式的字符串
public class HelloController {

    /**
     * 如果返回值是String类型，则按照原始格式返回
     * @return
     */
    @GetMapping("/hello")
    public String hello(){
        return "你好SpringBoot";
    }
}
