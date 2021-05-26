package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootDemo01Application {

    /**
     * 0.java需要一个运行的环境，环境内包括内存大小、线程大小、空间设定等，均在JVM中配置。-->JVM调优
     * 1.main方法中的args是jvm虚拟机传递过来的参数
     * 2.
     * @param args
     */
    public static void main(String[] args) {
        //传递的是class对象，运行期间通过反射的机制实例化对象
        SpringApplication.run(SpringbootDemo01Application.class, args);
    }

}
