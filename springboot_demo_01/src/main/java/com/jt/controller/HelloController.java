package com.jt.controller;

import com.jt.pojo.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Scanner;

@RestController //向前端返回JSON JSON：特殊格式的字符串
//spring容器加载指定的配置文件 看到注解里面含有Source字样，直接写"classpath:/"
//如果注解里的值只有一个value="xxx"，则value=可以省略不写，如果不止有value="xxx",则value=不可省略
@PropertySource(value = "classpath:/properties/hello.properties",encoding = "UTF-8")
public class HelloController {

    //需求：从容器中获取属性数据 springel表达式，又叫spel表达式
    //在yml配置文件中赋值，然后通过@Value("$xxx.xxx")注解获取值
    @Value("${hello.msg}")  //框架自动生成get/set方法，并不是不需要get/set方法
    private String msg;     //YML文件获取数据
    @Value("${jt.msg}")
    private String msg2;    //properties文件获取数据
    @Value("${abo.msg}")
    private String msg3;

    /**
     * 如果返回值是String类型，则按照原始格式返回
     * @return
     */
    @GetMapping("/hello")
    public String hello(){
        Car car = new Car();
        car.setName("特斯拉").setType("四轮车");
        return msg + "|" + msg2 + "|" + msg3;
    }
}
