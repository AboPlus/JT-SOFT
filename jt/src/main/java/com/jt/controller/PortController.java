package com.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PortController {

    @Value("${server.port}")
    private Integer port;

    /**
     * 需求：要求通过getPort方法  动态获取当前端口号的信息
     */
    @GetMapping("/getPort")
    public String getPort(){
        return "当前的端口号："+port;
    }
}
