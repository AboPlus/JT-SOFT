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

    //Spring容器<userMapper,代理对象>
    //面向接口编程 扩展性好
    @Autowired  //两种注入方式：name注入，类型注入
    private UserMapper userMapper;//JDK动态代理

    //关于测试类代码说明：要求：public 返回值void 方法名称不能叫Test
    @Test
    public void test01(){
        System.out.println(userMapper.getClass());
        List<User> userList = userMapper.findAll();
        System.out.println(userList);
    }

    //查询 用户的全部记录
    //缺点：只能用于单表查询
    @Test
    public void testFind(){
        //null表示不需要任何where条件，查询的是全部记录
        List<User> userList = userMapper.selectList(null);
        System.out.println(userList);
    }


}
