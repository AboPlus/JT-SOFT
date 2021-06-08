package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 刘昱江
 * 时间 2021/5/11
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public List<User> hello(){

        return userService.findAll();
    }
}
