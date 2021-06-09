package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/* VO: 后端服务器和前端服务器交互的一个媒介 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysResult {
    private Integer status; //定义状态码 200成功 201失败
    private String msg;     //服务器提示信息
    private Object data;    //服务器返回的数据

    //重载： 方法名相同 参数列表不同
    // 因为使用了static修饰，所以全局可以通过类名.方法名调用
    public static SysResult fail(){
        //因为是new了一个SysResult对象，所以里面参数顺序严格按照属性声明的顺序进行传值
        return new SysResult(201,"服务器处理失败",null);
    }

    public static SysResult success(){
        return new SysResult(200,"服务器处理成功",null);
    }

    public static SysResult success(Object data){
        return new SysResult(200,"服务器处理成功",data);
    }

    /*
    * 重载的原则： 参数之间不要耦合 JVM中理由“就近原则”导致业务有误
    * 如，如果传输了一个data参数为String JSON的话，系统会误认为是String msg当做msg接收参数
    * */
    /*public static SysResult success(String msg){
        return new SysResult(200,msg,null);
    }*/

    public static SysResult success(String msg,Object data){
        return new SysResult(200,msg,data);
    }



}
