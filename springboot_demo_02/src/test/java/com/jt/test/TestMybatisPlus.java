package com.jt.test;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestMybatisPlus {
    @Autowired
    private UserMapper userMapper;

    /*
    * MybatisPlus的工作原理
    * 1.通过注解实现对象和表一一映射（@TableName("xxx")）
    * 2.通过注解实现对象属性和表字段一一映射（@TableField("xxx") 如果属性名和字段名一致可省略不写）
    * 3.将公共方法进行抽取，抽取到BaseMapper接口中（Mapper接口继承BaseMapper）
    * 4.将用户操作的方法对象转化为数据库能识别的SQL语句
    *   转化过程：
    *       1.通过UserMapper查找继承的父级接口BaseMapper
    *       2.根据BaseMapper查找泛型对象User对象
    *       3.根据User对象中的注解（@TableName("demo_user")）找到数据表demo_user
    *       4.根据User对象中的属性上的注解（@TableField("xxx")）找到字段名
    *       5.在获取字段名的同时，获取属性的值，最后进行SQL拼接
    *       6.MP将拼接好的SQL交给Mybatis框架执行
    * */

    /*
    *   利用MP机制实现入库
    */
    @Test
    public void insert(){
        User user = new User();
        user.setName("张三").setSex("男").setAge(18);
        userMapper.insert(user);
    }


}
