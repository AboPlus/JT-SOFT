package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jt.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper //为接口创建了一个对象(反射机制)，JDK动态代理
//MP规则：BaseMapper<T> T表示MP要操作的表是谁？T必须引入对象（POJO）
public interface UserMapper extends BaseMapper<User> {
    //访问修饰符 返回值类型   方法名(参数...)

    //查询全部用户信息
    List<User> findAll();
}
