package com.jt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data                       //默认生成get/set/toString/hashcode....
@Accessors(chain = true)    //开启链式加载
@NoArgsConstructor          //无参构造
@AllArgsConstructor         //全参构造
public class Car {
    private Integer id;
    private String name;
    private String type;

    //pojo中必须有get/set方法

}
