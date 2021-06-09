package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author 刘昱江
 * 时间 2021/5/11
 */
@RestController
@CrossOrigin
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public List<User> hello(){
        return userService.findAll();
    }

    /**
     * 需求：根据用户名和密码实现用户登录，要求返回token密钥
     * URL：/user/login
     * 请求参数：post JSON {username:"xxx",password:"xxxx"}
     * 返回值：SysResult对象(token)
     */
    @PostMapping("/login")
    public SysResult login(@RequestBody User user){
        //执行后端登录操作，要求返回token密钥
        String token = userService.login(user);
        return StringUtils.hasLength(token) ? SysResult.success(token) : SysResult.fail();
    }
}
