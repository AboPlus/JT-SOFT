package com.jt.mapper;

import com.jt.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper //为接口创建了一个对象(反射机制)，JDK动态代理
public interface UserMapper {
    //访问修饰符 返回值类型   方法名(参数...)

    //查询全部用户信息
    List<User> findAll();
}
