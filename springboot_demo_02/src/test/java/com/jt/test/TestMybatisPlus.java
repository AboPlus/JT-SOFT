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
    *   MybatisPlus工作原理 利用MP机制实现入库
    *
    */
    @Test
    public void insert(){
        User user = new User();
        user.setName("张三").setSex("男").setAge(18);
        userMapper.insert(user);
    }


}
