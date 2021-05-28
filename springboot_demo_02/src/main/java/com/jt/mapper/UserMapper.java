package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jt.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

//@Mapper //为接口创建了一个对象(反射机制)，JDK动态代理
//MP规则：BaseMapper<T> T表示MP要操作的表是谁？T必须引入对象（POJO）
public interface UserMapper extends BaseMapper<User> {
    //访问修饰符 返回值类型   方法名(参数...)

    //查询全部用户信息
    List<User> findAll();

    @Insert("insert into demo_user(id,name,age,sex) values(null,#{name},#{age},#{sex})")
    void insertUser(User user);

    @Update("update demo_user set name=#{newName} where name=#{oldName}")
    void updateByName(String oldName, String newName);

    /* Mybatis中如果传递的参数只有一个，则名称任意，如name=#{abc},但是一般不这么使用 */
    @Delete("delete from demo_user where name=#{name}")
    void deleteByName(String name);
}
