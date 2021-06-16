package com.jt.config;

import com.jt.vo.SysResult;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/* 全局异常处理类 */
// 问题1：全局异常的处理 应该拦截哪一层代码？Controller 异常向上抛出，所以拦截Controller拦截的最全面
//@Configuration
//@ControllerAdvice + @ResponseBody
@RestControllerAdvice   //全局异常处理注解  返回值为JSON
public class MyExceptionConfig {
    //问题2：什么时候调用？ 有异常的时候调用
    // @ExceptionHandler(xxx.class) 表示捕获什么样类型的异常
    // @ExceptionHandler({RuntimeException.class, SqlSessionException.class})   //表示捕获多个异常
    @ExceptionHandler(RuntimeException.class)   //运行时异常时 进行捕获
    public Object handler(Exception exception){
        // 将报错信息控制台打印
        exception.printStackTrace();
        return SysResult.fail();
    }
}
