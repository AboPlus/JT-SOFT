package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //返回值是JSON数据
@CrossOrigin    //允许跨域的注解，加在controller层的类上或者方法上
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * URL:/addUser
     * method:post
     * 参数:form表单传参
     * 返回值:返回成功的字符信息
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public String insertUser(User user){
        userService.insertUser(user);
        return "新增用户成功";
    }

    /**
     * 查询User表的全部数据
     * 请求路径：http://localhost:8090/vue/getUser
     * 参数：不需要参数
     * 返回值：List<User>
     */
    @GetMapping("/getUser")
    public List<User> getUser(){
        return userService.getUser();
    }

}
