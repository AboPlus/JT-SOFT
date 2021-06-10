package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
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
}
