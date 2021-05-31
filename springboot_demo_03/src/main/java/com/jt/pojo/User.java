package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("demo_user")
//为了以后扩展方便A系统调用B系统中的数据要求实现序列化接口
//对象如果实现了序列化的接口之后，就会有对应的功能
public class User implements Serializable {
    //默认的序列化的编号 1L
    private static final long serialVersionUID = -2293941719496730677L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer age;
    private String name;
    private String sex;
}
