package com.jt.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class TestMybatisPlus02 {
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询id为23的数据
     */
    @Test
    public void select01(){
        User user = userMapper.selectById(23);
        System.out.println(user);
    }

    /**
     * 查询name=潘凤，sex=男 的数据
     * 方式一：可以通过对象的方式进行控制，需要通过对象只能实现=号的条件
     */
    @Test
    public void select02(){
        User user = new User();
        user.setName("潘凤").setSex("男");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    /**
     * 要求:年龄>18岁 sex=男 的用户
     */
    @Test
    public void select03(){
        Integer age = 18;
        String sex = "男";
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", age)
                    .eq("sex" , sex);
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    /**
     * 要求查询名字中包含"精"字的用户，并且按照age进行降序排列
     */
    @Test
    public void select04(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeLeft("name", "精")
                    .orderByDesc("age");
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    /**
     * 查询多个数据
     * 查询ID=1 3 6 7 的数据
     */
    @Test
    public void select05(){
        Integer[] ids = {1,3,6,7};
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    /**
     * 需求：查询name为null的数据
     */
    @Test
    public void select06(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("name");
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    /**
     * 动态sql查询：
     *  要求：根据age属性与sex属性进行查询
     *        如果其中数据为null，则不参与where条件的拼接
     *        where age>18 and sex="男"
     */
    @Test
    public void select07(){
        Integer age = 18;
        String sex = "男";
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt(age>0, "age", age)
                    .eq(StringUtils.hasLength(sex), "sex", sex);
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    /**
     * demo1
     * 只查询name,age的字段信息
     * queryWrapper.select("name","age");中的select表示挑选查询的字段
     * demo2:要求只返回name，age的字段
     */
    @Test
    public void select08(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name","age");
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    /**
     * demo2
     * 只查询name,age的字段信息
     * queryWrapper.select("name","age");中的select表示挑选查询的字段
     *  要求只返回name，age的字段 ———— selectMaps
     */
    @Test
    public void select09(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name","age")
                    .gt("age", 500)
                    .orderByDesc("age");
        List<Map<String, Object>> list = userMapper.selectMaps(queryWrapper);
        System.out.println(list);
    }

    /**
     * demo3
     * 查询性别为女的数据
     *  要求只第一列的数据 ———— selectObjs，可以不传参数，不传参数就位查询所有数据的第一列数据
     */
    @Test
    public void select10(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sex", "女");
        List<Object> objects = userMapper.selectObjs(queryWrapper);
        System.out.println(objects);
    }

}
