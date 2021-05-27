package com.jt.test;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//SpringBoot为了简化程序的测试，研发了@SpringBootTest注解
//该注解只能在测试包中使用
//作用：当程序执行@Test测试方法时，就会启动整个Spring容器
//Spring容器启动之后通过@Autowired注解从容器中获取实例化对象，之后完成业务的测试
@SpringBootTest
public class TestMybatis {

    //面向接口编程 扩展性好
    @Autowired
    private UserMapper userMapper;//JDK动态代理

    @Test
    public void testInsert(){
        System.out.println(userMapper.getClass());
        List<User> userList = userMapper.findAll();
        System.out.println(userList);
    }

}
