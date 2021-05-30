package com.jt.test;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class TestMybatis02 {
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询id为23的数据
     */
    @Test
    public void select01(){
        Integer id = 23 ;
        List<User> userList = userMapper.findUserById(id);
        System.out.println(userList);
    }

    /**
     * 查询name=潘凤，sex=男 的数据
     */
    @Test
    public void select02(){
        User user = new User();
        user.setName("潘凤").setSex("男");
        List<User> userList = userMapper.select02(user);
        System.out.println(userList);
    }

    /**
     * 要求:年龄>18岁 sex=男 的用户
     */
    @Test
    public void select03(){
        User user = new User();
        user.setAge(18).setSex("男");
        List<User> userList = userMapper.select03(user);
        System.out.println(userList);
    }

    /**
     * 要求查询名字中包含"精"字的用户，并且按照age进行降序排列
     */
    @Test
    public void select04(){
        String name = "%精%";
        List<User> userList = userMapper.select04(name);
        System.out.println(userList);
    }

    /**
     * 查询ID=1 3 6 7 的数据
     */
    @Test
    public void select05(){

    }

    /**
     * 需求：查询name为null的数据
     */
    @Test
    public void select06(){
        List<User> userList = userMapper.select06();
        System.out.println(userList);
    }

    /**
     * 根据age属性与sex属性进行查询
     *  age>18 and sex="男"
     */
    @Test
    public void select07(){
        User user = new User();
        user.setAge(18).setSex("男");
        List<User> list = userMapper.select07(user);
        System.out.println(list);
    }

    /**
     * 只查询name,age的字段信息
     */
    @Test
    public void select08(){
        List<User> userList = userMapper.select08();
        System.out.println(userList);
    }

    /**
     * 查询name,age的字段信息,要求只返回name，age的字段
     */
    @Test
    public void select09(){
        List<Map<String,Object>> userList = userMapper.select09();
        System.out.println(userList);
    }

    /**
     * 查询性别为女的数据,只返回第一列的数据
     */
    @Test
    public void select10(){
        User user = new User();
        user.setSex("女");
        List<Object> userList = userMapper.select10(user);
        System.out.println(userList);
    }
}
