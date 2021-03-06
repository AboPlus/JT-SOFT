package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.vo.PageResult;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.UUID;

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

    /**
     *   业务需求：根据条件进行分页查询
     * - 请求路径: /user/list
     * - 请求类型: GET
     * - 请求参数: 后台使用PageResult对象接收
     * - 请求案例: http://localhost:8091/user/list?query=查询关键字&pageNum=1&pageSize=10
     * - 响应参数: SysResult对象 需要携带分页对象 PageResult
     * 分析：
     *      1.用户传递参数有三个(query,pageNum,pageSize)
     *      2.要求返回值有五个(query,pageNum,pageSize,total,rows)
     */
    @GetMapping("/list")
    public SysResult list(PageResult pageResult){
        pageResult = userService.findUserByPage(pageResult);
        return SysResult.success(pageResult);
    }

    /**
     *   用户状态修改
     * - 请求路径 /user/status/{id}/{status}
     * - 请求类型 PUT
     * - 请求参数: 用户ID/状态值数据
     * - 返回值结果: SysResult对象
     */
    @PutMapping("/status/{id}/{status}")
    public SysResult updateStatus(User user){
        userService.updateStatus(user);
        return SysResult.success();
    }

    /**  用户删除
     * - 请求路径: /user/{id}
     * - 请求类型: delete
     * - 请求参数:
     * - 返回值: SysResult对象
     */
    @DeleteMapping("/{id}")
    public SysResult deleteUserById(@PathVariable Integer id){
        userService.deleteUserById(id);
        return SysResult.success();
    }

    /**
     *   业务需求：实现用户新增
     * - 请求路径 /user/addUser
     * - 请求类型 POST
     * - 请求参数: 整个form表单数据
     * - 返回值结果: SysResult对象
     */
    @PostMapping("/addUser")
    public SysResult addUser(@RequestBody User user){
        boolean flag = userService.addUser(user);
        return flag?SysResult.success():SysResult.fail();
    }

    /**
     *   业务需求：用户修改 ———— 根据ID查询用户信息
     * - 请求路径: /user/{id}
     * - 请求类型: GET
     * - 返回值: SysResult对象
     */
    @GetMapping("/{id}")
    public SysResult selectUserById(@PathVariable Integer id){
        User user = userService.selectUserById(id);
        return user==null?SysResult.fail():SysResult.success(user);
    }

    /**
     *   业务需求：用户修改 ———— 根据用户ID更新数据
     * - 请求路径: /user/updateUser
     * - 请求类型: PUT
     * - 请求参数:id、phone、email
     * - 返回值: SysResult对象
     */
    @PutMapping("/updateUser")
    public SysResult updateUser(@RequestBody User user){
        Boolean flag = userService.updateUser(user);
        return flag?SysResult.success():SysResult.fail();
    }

}
