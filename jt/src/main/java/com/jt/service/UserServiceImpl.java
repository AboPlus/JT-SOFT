package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author 刘昱江
 * 时间 2021/5/11
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }


    /**
     * 1.将明文转化为密文 -- MD5加密
     * 2.通过username和password查询数据库
     * 3.
     *      有数据： 用户名和密码正确 返回UUID(token)
     *      没有数据：用户名和密码错误 返回null
     * @param user
     * @return
     */
    @Override
    public String login(User user) {
        String password = user.getPassword();
        //md5hash加密 相对于 md5加密更加"安全"
        //getBytes() -- 使用平台的默认字符集将字符串编码为 byte 序列，并将结果存储到一个新的 byte 数组中
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(md5Password);
        // 根据其中补位null的数据当做where条件 u/p
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        User userDB = userMapper.selectOne(queryWrapper);
        /* randomUUID()的返回值是UUID对象，而我们声明的token是String类型，所以使用toString转化为String类型
        * UUID格式：7788fccd-c8ff-11eb-b2fa-4ccc6ae2fd3c  所以要把-换成空串( replace() )
        * */
        String token = UUID.randomUUID().toString().replace("-", "");
        return userDB==null?null:token;
    }

    /**
     * 分页Sql:
     *    select * from user limit 数据起始位置,查询的条数
     * 查询第一页 每页20条
     *    select * from user limit 0,20     [0,20)
     * 查询第二页 每页20条
     *    select * from user limit 20,20     [20,20)
     * 查询第三页 每页20条
     *    select * from user limit 40,20     [40,20)
     * 查询第N页，每页20条
     *    select * from user limit (n-1)*20,20
     *
     * 查询带查询条件的SQL：
     *      select * from user where name like "%#{query}%" limit (n-1)20,20
     *
     * 关于分页查询的说明：
     *      userMapper.selectPage
     */
    @Override
    public PageResult findUserByPage(PageResult pageResult) {
        Integer num = pageResult.getPageNum();
        Integer size = pageResult.getPageSize();
        //1.定义分页对象
        IPage<User> page = new Page<>(num,size);//向上造型  多态的体现
        //2.构建条件构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        boolean flag = StringUtils.hasLength(pageResult.getQuery());
        // 根据判断条件决定是否拼接where条件
        queryWrapper.like(flag, "username", pageResult.getQuery());
        //MP提供的分页查询的方法，返回值是Page分页对象
        page = userMapper.selectPage(page, queryWrapper);
        long total = page.getTotal();
        List<User> userList = page.getRecords(); //MP帮助实现了分页查询结果
        pageResult.setRows(userList).setTotal(total);
        return pageResult;
    }

    // user:{id:xxx,status:true/false}
    @Override
    public void updateStatus(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteById(id);
    }


}
