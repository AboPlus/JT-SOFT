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
     * 结果：一项:userMapper.selectOne();
     *      多项:userMapper.selectList();
     * SQL: ... where name="xxx" and sex="xxx"
     * queryWrapper:条件构造器  用于拼接where条件
     * 如果遇到多条件查询，则默认的连接符为and  如：SELECT id,name,age,sex FROM demo_user WHERE name=? AND sex=?
     * 方式一：可以通过对象的方式进行控制，需要通过对象只能实现=号的条件
     */
    @Test
    public void select02(){
        User user = new User();
        user.setName("潘凤").setSex("男");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        //根据对象中不为null的属性拼接where条件
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    /*
    * 要求:年龄>18岁 sex=男 的用户
    * 转义字符：
    *   >  gt   (greater than)
    *   <  lt   (less than)
    *   =  eq   (equal)
    *   >= ge   (great than or equal)
    *   <= le   (less than or equal)
    *   <> ne   (not equal)
    * */
    @Test
    public void select03(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 18)
                    .or()
                    .eq("sex", "男");
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    /**
     * 要求查询名字中包含"精"字的用户，并且按照age进行降序排列
     * SQL: like "%精%"  包含精
     *      like "精%"   以精开头
     *      like "%精"   以精结尾
     */
    @Test
    public void select04(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        /*queryWrapper.likeLeft("name", "精")  //百分号在左就是likeLeft 百分号在右就是likeRight
                    .orderByDesc("age");*/
        queryWrapper.like("name", "%精%")  //或者直接使用like  手动添加%
                    .orderByDesc("age");
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    /**
     * 查询多个数据
     * 查询ID=1 3 6 7 的数据
     * where id in(xx,xx,xx,xx)
     * 注*如遇到多值传参，一般采用对象的方式封装，因为基本类型没有get/set方法，无法取值
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
     *  正确SQL：SELECT id,name,age,sex FROM demo_user WHERE (age > 18 AND sex = "男")
     *  错误SQL：SELECT id,name,age,sex FROM demo_user WHERE (age > 18 AND sex = null)
     *  MP实现动态查询：
     *      参数1：condition boolean类型数据   true    拼接条件
     *                                        false   不拼接条件
     *      参数2：字段名
     *      参数3：字段值
     *  如果age和sex都不符合规定，则查询的是全部数据
     */
    @Test
    public void select07(){
        Integer age = 18;
        String sex = "男";
        //boolean flag = sex != null && sex.length()>0;
        boolean flag = StringUtils.hasLength(sex);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt(age>0, "age", age)
                    .eq(flag,"sex" ,sex );
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
        //没查询的数据以null返回
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
        queryWrapper.select("name","age");
        //没查询的数据以null返回
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
        //没查询的数据以null返回
        List<Object> list = userMapper.selectObjs(queryWrapper);
        System.out.println(list);
    }


    /**
     * 将id=234的用户名称改为"李四"
     */
    @Test
    public void updateUser(){
        User user = new User();
        user.setId(234).setName("李四");
        //UPDATE demo_user SET name=? WHERE id=?
        userMapper.updateById(user);
        System.out.println("修改用户信息成功！");
    }

}
