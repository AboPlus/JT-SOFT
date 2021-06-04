package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 根据ID查询user对象
     * URL:http://localhost:8090/vue/getUserById?id=100
     */
    @GetMapping("/getUserById")
    public User getUserById(Integer id){
        return userService.getUserById(id);
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    /**
     * 根据ID删除user对象
     * URL:http://localhost:8090/vue/deleteUserById
     */
    @DeleteMapping("/deleteUserById")
    public String deleteUserById(Integer id){
        userService.deleteUserById(id);
        return "数据删除成功";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Integer id){
        userService.deleteUserById(id);
        return "删除成功！！";
    }

    /**
     * 接收post请求
     * URL：http://localhost:8090/vue/addUser
     * 传递的参数是一个JSON串 ———— {name: "张三", age: 18, sex: "男"}
     * 解决方案：可以将JSON数据根据 key:value 转化为对象 ———— @RequestBody(要求对象拥有get/set方法)
     */
    @PostMapping("/addUsers")
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "新增用户成功";
    }

    @PostMapping("/addUserForm")
    public String addUserForm(User user){
        userService.addUser(user);
        return "from表单新增用户成功";
    }

    @PostMapping("/user/{name}/{age}/{sex}")
    public String addUserRestFul(User user){
        userService.addUser(user);
        return "restFull风格新增用户成功";
    }

    @PutMapping("/user/{id}/{name}")
    public String updateUser(User user){
        userService.updateUser(user);
        return "修改用户信息成功";
    }

    /**
     * 实现修改操作
     * 参数：user的JSON数据
     */
    @PutMapping("/updateUser")
    public String updateUserInfo(@RequestBody User user){
        userService.updateById(user);
        return "修改用户信息成功!!";
    }

}
