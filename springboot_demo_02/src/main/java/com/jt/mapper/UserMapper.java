package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

//@Mapper //为接口创建了一个对象(反射机制)，JDK动态代理
//MP规则：BaseMapper<T> T表示MP要操作的表是谁？T必须引入对象（POJO）
public interface UserMapper extends BaseMapper<User> {
    //访问修饰符 返回值类型   方法名(参数...)

    //查询全部用户信息
    List<User> findAll();

    @Insert("insert into demo_user(id,name,sex,age) values(null,#{name},#{age},#{sex})")
    void insertUser(User user);

    @Update("update demo_user set name=#{newName},age=#{newAge} where name=#{oldName}")
    void updateByName(String oldName, String newName, Integer newAge);

    /* Mybatis中如果传递的参数只有一个，则名称任意，如name=#{abc},但是一般不这么使用 */
    @Delete("delete from demo_user where name=#{name}")
    void deleteByName(String name);

    @Select("select * from demo_user where id=#{id}")
    List<User> findUserById(Integer id);

    @Select("select * from demo_user where name=#{name} and sex=#{sex}")
    List<User> select02(User user);

    @Select("select * from demo_user where age>#{age} and sex=#{sex}")
    List<User> select03(User user);

    @Select("select * from demo_user where name like #{name} order by age desc")
    List<User> select04(String name);

    @Select("select * from demo_user where name is null")
    List<User> select06();

    @Select("select * from demo_user where age>#{age} and sex=#{sex}")
    List<User> select07(User user);

    @Select("select name,age from demo_user")
    List<User> select08();

    @Select("select name,age from demo_user")
    List<Map<String, Object>> select09();

    @Select("select * from demo_user where sex=#{sex}")
    List<Object> select10(User user);
}
