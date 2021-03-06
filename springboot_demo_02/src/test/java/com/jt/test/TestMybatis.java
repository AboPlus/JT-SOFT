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

    @Test
    public void insert(){
        User user = new User();
        user.setName("张三").setAge(18).setSex("男");
        userMapper.insertUser(user);
        System.out.println("新增用户成功！");
    }

    //将姓名为张三的用户的姓名改为李四,年龄改为20
    @Test
    public void update(){
        String oldName="张三";
        String newName="李四";
        Integer newAge=20;
        userMapper.updateByName(oldName,newName,newAge);
        System.out.println("修改用户成功！");
    }

    //将姓名为李四的用户数据删除
    @Test
    public void delete(){
        String name="李四";
        userMapper.deleteByName(name);
        System.out.println("删除用户成功!");
    }
}
