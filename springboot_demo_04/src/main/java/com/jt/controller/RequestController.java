package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestController {

    /*
    *   概念: 转发一般都是发生在服务器端. 由服务器内部进行路径跳转.
        特点:
        1.用户的请求的路径地址不发生变化.
        2.转发时可以携带用户信息.(request对象)
        3.转发时用户的请求 只请求了一次
    * */
    @GetMapping("/getA")
    /* 返回值固定是String类型 */
    public String getA(Integer id){
        System.out.println("我是A请求" + id);
        //当用户在访问A时，由服务器动态将请求发送给B
        //关键字： forward
        return "forward:/getB";
    }
    @GetMapping("/getB")
    @ResponseBody
    public String getB(Integer id){
        System.out.println("我是B请求" + id);
        return "我是B的服务器"+ id;
    }

    /*
    *   说明: 由于特殊的业务需求,用户请求服务器时,要求用户自己访问其他的服务器,才能获取结果.
        特点:
        1.用户的请求的路径地址发生变化.
        2.重定向时,请求不会携带用户的数据()
        3.请求多次,响应多次的,request对象不是同一个.
    * */
    @GetMapping("/getC")
    public String getC(Integer id){
        System.out.println("我是A请求" + id);
        //关键字： redirect
        return "redirect:/getD";
    }
    @GetMapping("/getD")
    @ResponseBody
    public String getD(Integer id){
        System.out.println("我是B请求" + id);
        return "我是B的服务器"+ id;
    }
}
